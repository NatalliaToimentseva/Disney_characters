package com.example.disney_characters.ui.details.features

import com.example.disney_characters.models.CharacterMainData
import com.example.disney_characters.repository.CharactersDbRepository
import com.example.disney_characters.utils.toCharacterMainData
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val charactersDbRepository: CharactersDbRepository
) {

    suspend fun getCharacterById(id: Int): CharacterMainData? {
        return charactersDbRepository.getCharacterById(id)?.toCharacterMainData()
    }
}