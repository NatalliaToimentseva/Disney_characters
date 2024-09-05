package com.example.disney_characters.repository.repositoryImpl

import com.example.disney_characters.dataSources.roomDB.CharactersDao
import com.example.disney_characters.dataSources.roomDB.entities.CharacterDbEntity
import com.example.disney_characters.repository.CharactersDbRepository
import javax.inject.Inject

class CharactersRoomRepository @Inject constructor(
    private val charactersDao: CharactersDao
) : CharactersDbRepository {

    override fun getFavoriteCharacters() = charactersDao.getAllCharacters()

    override suspend fun getCharacterById(id: Int) = charactersDao.getCharacterById(id)

    override suspend fun addToFavorite(character: CharacterDbEntity) =
        charactersDao.addCharacter(character)

    override suspend fun deleteFromFavorite(id: Int) = charactersDao.deleteCharacterById(id)
}