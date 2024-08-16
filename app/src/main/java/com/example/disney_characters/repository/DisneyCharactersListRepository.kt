package com.example.disney_characters.repository

import com.example.disney_characters.repository.domain.CharactersResult
import com.example.disney_characters.ui.home.domain.HomeResult

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): HomeResult

    suspend fun getCharacterById(id: Int): CharactersResult
}