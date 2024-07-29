package com.example.disney_characters.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disney_characters.ui.models.CharacterMainData
import com.example.disney_characters.ui.repository.DisneyCharactersListRepository
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

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            character.postValue(repository.getCharacterById(id))
            isInProgress.postValue(false)
        }
    }
}