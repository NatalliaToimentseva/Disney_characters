package com.example.disney_characters.repository

import com.example.disney_characters.dataSources.roomDB.entities.CharacterDbEntity
import kotlinx.coroutines.flow.Flow

interface CharactersDbRepository {

    fun getFavoriteCharacters(): Flow<List<CharacterDbEntity>>

    suspend fun getCharacterById(id: Int): CharacterDbEntity?

    suspend fun addToFavorite(character: CharacterDbEntity)

    suspend fun deleteFromFavorite(id: Int)
}