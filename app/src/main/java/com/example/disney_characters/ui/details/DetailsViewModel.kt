package com.example.disney_characters.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.models.CharacterMainData
import com.example.disney_characters.ui.details.domain.DetailsResult
import com.example.disney_characters.ui.details.features.AddToFavoriteUseCase
import com.example.disney_characters.ui.details.features.DeleteFromFavoriteUseCase
import com.example.disney_characters.ui.details.features.GetCharacterByIdUseCase
import com.example.disney_characters.ui.details.features.NetworkLoadCharacterUseCase
import com.example.disney_characters.ui.home.TEMP_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private val mock = hashMapOf(
    1 to CharacterItemModel(1, "Test1", TEMP_URL),
    2 to CharacterItemModel(2, "Test2", TEMP_URL),
    3 to CharacterItemModel(3, "Test3", TEMP_URL),
    4 to CharacterItemModel(4, "Test4", TEMP_URL),
    5 to CharacterItemModel(5, "Test5", TEMP_URL),
    6 to CharacterItemModel(6, "Test6", TEMP_URL),
    7 to CharacterItemModel(7, "Test7", TEMP_URL)
)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val networkLoadCharacterUseCase: NetworkLoadCharacterUseCase
) : ViewModel() {

    val character = MutableLiveData<CharacterMainData?>(null)
    val isInProgress = MutableLiveData(false)
    var error = MutableLiveData<String?>(null)

    fun clearError() {
        error.value = null
    }

    fun selectCharacterLoader(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCharacterByIdUseCase.getCharacterById(id)
            if (result != null) {
                character.postValue(result)
            } else {
                getCharacter(id)
            }
        }
    }

    fun selectIsFavorite(id: Int) {
        character.value?.let {
            if (it.isFavorite == true) {
                character.value = it.copy(isFavorite = false)
                deleteFromFavorite(id)
            } else {
                character.value = it.copy(isFavorite = true)
                addToFavorite(id)
            }
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            when (val result = networkLoadCharacterUseCase.loadCharacterById(id)) {
                is DetailsResult.Success -> character.postValue(result.data)
                is DetailsResult.Error -> {
                    result.throwable.message?.let { error.postValue(it) }
                    //MOCK
                    character.postValue(
                        CharacterMainData(
                            name = mock[id]!!.name,
                            imgUrl = mock[id]!!.imageUrl,
                            isFavorite = null,
                            fields = null
                        )
                    )
                }
            }
            isInProgress.postValue(false)
        }
    }

    private fun addToFavorite(id: Int) {
        character.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                isInProgress.postValue(true)
                addToFavoriteUseCase.addCharacterToFavorite(
                    CharacterItemModel(
                        id = id,
                        name = it.name,
                        imageUrl = it.imgUrl
                    )
                )
                isInProgress.postValue(false)
            }
        }
    }

    private fun deleteFromFavorite(id: Int) {
        character.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                isInProgress.postValue(true)
                deleteFromFavoriteUseCase.deleteFromFavorite(id)
                isInProgress.postValue(false)
            }
        }
    }
}