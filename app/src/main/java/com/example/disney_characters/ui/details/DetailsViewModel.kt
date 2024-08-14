package com.example.disney_characters.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disney_characters.models.CharacterMainData
import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DisneyCharactersListRepository
) : ViewModel() {

    val character = MutableLiveData<CharacterMainData?>(null)
    val isInProgress = MutableLiveData(false)
    var error = MutableLiveData<String?>(null)

    fun clearError() {
        error.value = null
    }

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            when (val result = repository.getCharacterById(id)) {
                is Result.Success<*> -> character.postValue(result.data as CharacterMainData)
                is Result.Error -> {
                    result.throwable.message?.let { error.postValue(it) }
                }
            }
            isInProgress.postValue(false)
        }
    }
}