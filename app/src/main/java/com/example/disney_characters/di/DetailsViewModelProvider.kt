package com.example.disney_characters.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.disney_characters.ui.details.DetailsViewModel
import com.example.disney_characters.ui.details.features.AddToFavoriteUseCase
import com.example.disney_characters.ui.details.features.DeleteFromFavoriteUseCase
import com.example.disney_characters.ui.details.features.GetCharacterByIdUseCase
import com.example.disney_characters.ui.details.features.NetworkLoadCharacterUseCase
import javax.inject.Inject

class DetailsViewModelProvider @Inject constructor(
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val networkLoadCharacterUseCase: NetworkLoadCharacterUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(
            addToFavoriteUseCase,
            deleteFromFavoriteUseCase,
            getCharacterByIdUseCase,
            networkLoadCharacterUseCase
        ) as T
    }
}