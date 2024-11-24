package com.example.disney_characters.repository

import com.example.disney_characters.dataSources.network.entities.CharacterEntity
import com.example.disney_characters.dataSources.network.entities.ListCharactersEntity
import retrofit2.Response

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): Response<ListCharactersEntity>

    suspend fun getCharacterById(id: Int): Response<CharacterEntity>
}