package com.example.disney_characters.repository.repositoryImpl

import com.example.disney_characters.dataSources.network.DisneyApi
import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.domain.CharactersResult
import com.example.disney_characters.ui.home.domain.HomeResult
import com.example.disney_characters.utils.toCharacterMainData
import com.example.disney_characters.utils.toListCharacterModel
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override suspend fun getListCharacters(): HomeResult {
        val response = api.getAllCharacters()
        return if (response.isSuccessful) {
            HomeResult.Success(response.body()?.toListCharacterModel() ?: arrayListOf())
        } else {
            HomeResult.Error(Throwable(response.message()))
        }
    }

    override suspend fun getCharacterById(id: Int): CharactersResult {
        val response = api.getCharacter(id)
        return if (response.isSuccessful) {
            CharactersResult.Success(response.body()?.toCharacterMainData())
        } else {
            CharactersResult.Error(Throwable(response.message()))
        }
    }
}