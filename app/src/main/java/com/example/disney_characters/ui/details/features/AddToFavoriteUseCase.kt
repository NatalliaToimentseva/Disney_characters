package com.example.disney_characters.ui.details.features

import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.repository.CharactersDbRepository
import com.example.disney_characters.utils.toCharacterDBEntity
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val charactersDbRepository: CharactersDbRepository
) {

    suspend fun addCharacterToFavorite(character: CharacterItemModel) {
        charactersDbRepository.addToFavorite(character.toCharacterDBEntity())
    }
}