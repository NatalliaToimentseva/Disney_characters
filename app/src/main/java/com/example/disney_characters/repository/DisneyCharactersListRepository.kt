package com.example.disney_characters.repository

import com.example.disney_characters.dataSources.network.responses.CharacterResponse
import com.example.disney_characters.dataSources.network.responses.ListCharactersResponse
import retrofit2.Response

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): Response<ListCharactersResponse>

    suspend fun getCharacterById(id: Int): Response<CharacterResponse>
}