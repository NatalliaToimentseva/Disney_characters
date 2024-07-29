package com.example.disney_characters.dataSources.repository

import com.example.disney_characters.dataSources.DisneyApi
import com.example.disney_characters.ui.models.CharacterItemModel
import com.example.disney_characters.ui.models.CharacterMainData
import com.example.disney_characters.ui.repository.DisneyCharactersListRepository
import com.example.disney_characters.utils.toCharacterMainData
import com.example.disney_characters.utils.toListCharacterModel
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override suspend fun getListCharacters(): List<CharacterItemModel> {
        val response = api.getAllCharacters()
        var listCharacters: List<CharacterItemModel>? = null
        if (response.isSuccessful) {
            listCharacters = response.body()?.toListCharacterModel()
        }
        return listCharacters ?: arrayListOf()
    }

    override suspend fun getCharacterById(id: Int): CharacterMainData? {
        val response = api.getCharacter(id)
        var character: CharacterMainData? = null
        if (response.isSuccessful) {
            character = response.body()?.toCharacterMainData()
        }
        return character
    }
}