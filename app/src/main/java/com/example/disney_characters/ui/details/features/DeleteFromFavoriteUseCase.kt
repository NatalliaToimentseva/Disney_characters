package com.example.disney_characters.ui.details.features

import com.example.disney_characters.repository.CharactersDbRepository
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val charactersDbRepository: CharactersDbRepository
) {

    suspend fun deleteFromFavorite(id: Int) {
        charactersDbRepository.deleteFromFavorite(id)
    }
}