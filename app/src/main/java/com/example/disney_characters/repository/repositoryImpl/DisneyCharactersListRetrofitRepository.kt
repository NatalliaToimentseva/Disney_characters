package com.example.disney_characters.repository.repositoryImpl

import com.example.disney_characters.dataSources.network.DisneyApi
import com.example.disney_characters.repository.DisneyCharactersListRepository
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override suspend fun getListCharacters() = api.getAllCharacters()

    override suspend fun getCharacterById(id: Int) = api.getCharacter(id)
}