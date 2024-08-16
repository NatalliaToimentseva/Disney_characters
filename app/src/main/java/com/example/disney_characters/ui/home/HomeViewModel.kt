package com.example.disney_characters.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.ui.home.domain.HomeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TEMP_URL =
    "https://static.wikia.nocookie.net/disney/images/1/15/Arianna_Tangled.jpg/revision/latest?cb=20160715191802"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyCharactersListRepository,
    private val getFavoriteCharactersListUseCase: GetFavoriteCharactersListUseCase
) : ViewModel() {

    val disneyCharactersList = MutableLiveData<List<CharacterItemModel>?>()
    val isInProgress = MutableLiveData(false)
    val isFavoriteList = MutableLiveData(false)
    val isAllList = MutableLiveData(true)
    var error = MutableLiveData<String?>(null)

    fun clearError() {
        error.value = null
    }

    fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            handleResult(repository.getListCharacters())
        }
    }

    fun selectAllList() {
        isAllList.value = true
        isFavoriteList.value = false
        loadListData()
    }

    fun selectFavorite() {
        isFavoriteList.value = true
        isAllList.value = false
        loadFavoriteData()
    }

    private fun loadFavoriteData() {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            getFavoriteCharactersListUseCase.loadCharactersFavoriteList().collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: HomeResult) {
        when (result) {
            is HomeResult.Success -> {
                if (result.data.isNotEmpty()) {
                    disneyCharactersList.postValue(result.data)
                } else {
                    disneyCharactersList.postValue(null)
                }
                isInProgress.postValue(false)
            }

            is HomeResult.Error -> {
                result.throwable.message?.let { error.postValue(it) }
                disneyCharactersList.postValue(
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
                isInProgress.postValue(false)
            }
        }
    }
}