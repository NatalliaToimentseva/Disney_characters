package com.example.disney_characters.ui.details.features

import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.ui.details.Domain.DetailsResult
import com.example.disney_characters.utils.toCharacterMainData
import javax.inject.Inject

class NetworkLoadCharacterUseCase @Inject constructor(
    private val disneyCharactersListRepository: DisneyCharactersListRepository
) {

    suspend fun loadCharacterById(id: Int): DetailsResult {
        val response = disneyCharactersListRepository.getCharacterById(id)
        return if (response.isSuccessful) {
            DetailsResult.Success(response.body()?.toCharacterMainData())
        } else {
            DetailsResult.Error(Throwable(response.message()))
        }
    }
}