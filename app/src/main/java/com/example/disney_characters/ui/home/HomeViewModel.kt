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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyCharactersListRepository
) : ViewModel() {

    val disneyCharactersList = MutableLiveData<List<CharacterItemModel>>()
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