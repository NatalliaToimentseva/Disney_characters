package com.example.disney_characters.repository

import com.example.disney_characters.dataSources.entities.CharacterEntity
import com.example.disney_characters.dataSources.entities.ListCharactersEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface DisneyCharactersListRepository {

    fun getListCharacters(): Observable<Response<ListCharactersEntity>>

    fun getCharacterById(id: Int): Single<Response<CharacterEntity>>
}