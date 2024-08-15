package com.example.disney_characters.dataSources

import com.example.disney_characters.dataSources.entities.CharacterEntity
import com.example.disney_characters.dataSources.entities.ListCharactersEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyApi {

    @GET("character")
    fun getAllCharacters() : Observable<Response<ListCharactersEntity>>

    @GET("character/{id}")
    fun getCharacter(
        @Path ("id") id: Int
    ): Single<Response<CharacterEntity>>
}