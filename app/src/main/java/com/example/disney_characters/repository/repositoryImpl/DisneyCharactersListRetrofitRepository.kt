package com.example.disney_characters.repository.repositoryImpl

import com.example.disney_characters.dataSources.DisneyApi
import com.example.disney_characters.repository.DisneyCharactersListRepository
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override fun getListCharacters() = api.getAllCharacters()

    override fun getCharacterById(id: Int) = api.getCharacter(id)
}