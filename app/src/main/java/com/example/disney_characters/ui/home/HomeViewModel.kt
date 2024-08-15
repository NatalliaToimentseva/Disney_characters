package com.example.disney_characters.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TEMP_URL =
    "https://static.wikia.nocookie.net/disney/images/1/15/Arianna_Tangled.jpg/revision/latest?cb=20160715191802"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyCharactersListRepository
) : ViewModel() {

    val disneyCharactersList = MutableLiveData<List<CharacterItemModel>>(
        arrayListOf(
            CharacterItemModel(1, "Test1", TEMP_URL),
            CharacterItemModel(2, "Test2", TEMP_URL),
            CharacterItemModel(3, "Test3", TEMP_URL),
            CharacterItemModel(4, "Test4", TEMP_URL),
            CharacterItemModel(5, "Test5", TEMP_URL),
            CharacterItemModel(6, "Test6", TEMP_URL),
            CharacterItemModel(7, "Test7", TEMP_URL)
        )
    )
    val isInProgress = MutableLiveData(false)
    var error = MutableLiveData<String?>(null)

    fun clearError() {
        error.value = null
    }

    fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            when (val result = repository.getListCharacters()) {
                is Result.Success<*> -> {
                    disneyCharactersList.postValue(result.data as List<CharacterItemModel>)
                }

                is Result.Error -> {
                    result.throwable.message?.let { error.postValue(it) }
                }
            }
            isInProgress.postValue(false)
        }
    }
}