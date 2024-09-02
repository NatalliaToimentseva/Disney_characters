package com.example.disney_characters.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.disney_characters.ui.home.HomeViewModel
import com.example.disney_characters.ui.home.features.GetFavoriteCharactersListUseCase
import com.example.disney_characters.ui.home.features.NetworkLoadAllCharactersUseCase
import javax.inject.Inject

class HomeViewModelProvider @Inject constructor(
    private val getFavoriteCharactersListUseCase: GetFavoriteCharactersListUseCase,
    private val networkLoadAllCharactersUseCase: NetworkLoadAllCharactersUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getFavoriteCharactersListUseCase, networkLoadAllCharactersUseCase) as T
    }
}