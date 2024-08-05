package com.example.disney_characters.repository.repositoryImpl

import com.example.disney_characters.dataSources.DisneyApi
import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.domain.Result
import com.example.disney_characters.utils.toCharacterMainData
import com.example.disney_characters.utils.toListCharacterModel
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override suspend fun getListCharacters(): Result {
        val response = api.getAllCharacters()
        return if (response.isSuccessful) {
            Result.Success(response.body()?.toListCharacterModel())
        } else {
            Result.Error(Throwable(response.message()))
        }
    }

    override suspend fun getCharacterById(id: Int): Result {
        val response = api.getCharacter(id)
        return if (response.isSuccessful) {
            Result.Success(response.body()?.toCharacterMainData())
        } else {
            Result.Error(Throwable(response.message()))
        }
    }
}