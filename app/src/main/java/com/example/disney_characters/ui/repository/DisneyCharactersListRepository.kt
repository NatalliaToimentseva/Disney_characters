package com.example.disney_characters.ui.repository

import com.example.disney_characters.ui.models.CharacterItemModel
import com.example.disney_characters.ui.models.CharacterMainData

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): List<CharacterItemModel>

    suspend fun getCharacterById(id: Int): CharacterMainData?
}