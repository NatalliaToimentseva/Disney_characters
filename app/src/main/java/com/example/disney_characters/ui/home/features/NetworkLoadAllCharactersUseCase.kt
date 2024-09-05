package com.example.disney_characters.ui.home.features

import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.ui.home.domain.HomeResult
import com.example.disney_characters.utils.toListCharacterModel
import javax.inject.Inject

class NetworkLoadAllCharactersUseCase @Inject constructor(
    private val disneyCharactersListRepository: DisneyCharactersListRepository
) {

    suspend fun loadCharactersList(): HomeResult {
        val response = disneyCharactersListRepository.getListCharacters()
        return if (response.isSuccessful) {
            HomeResult.Success(response.body()?.toListCharacterModel() ?: arrayListOf())
        } else {
            HomeResult.Error(Throwable(response.message()))
        }
    }
}