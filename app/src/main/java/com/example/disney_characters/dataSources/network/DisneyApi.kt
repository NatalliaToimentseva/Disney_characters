package com.example.disney_characters.dataSources.network

import com.example.disney_characters.dataSources.network.responses.CharacterResponse
import com.example.disney_characters.dataSources.network.responses.ListCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyApi {

    @GET("character")
    suspend fun getAllCharacters() : Response<ListCharactersResponse>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path ("id") id: Int
    ): Response<CharacterResponse>
}