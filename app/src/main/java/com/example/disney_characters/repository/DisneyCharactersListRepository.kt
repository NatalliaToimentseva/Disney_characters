package com.example.disney_characters.repository

import com.example.disney_characters.repository.domain.Result

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): Result

    suspend fun getCharacterById(id: Int): Result
}