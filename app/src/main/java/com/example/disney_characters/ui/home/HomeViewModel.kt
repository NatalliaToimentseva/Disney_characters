package com.example.disney_characters.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disney_characters.ui.models.CharacterItemModel
import com.example.disney_characters.ui.repository.DisneyCharactersListRepository
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

    fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            isInProgress.postValue(true)
            disneyCharactersList.postValue(repository.getListCharacters())
            isInProgress.postValue(false)
        }
    }
}