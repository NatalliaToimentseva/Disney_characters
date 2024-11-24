package com.example.disney_characters.dataSources.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.disney_characters.dataSources.roomDB.entities.CharacterDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT * FROM Favorite_characters")
    fun getAllCharacters(): Flow<List<CharacterDbEntity>>

    @Query("SELECT * FROM Favorite_characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterDbEntity?


    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addCharacter(character: CharacterDbEntity)


    @Query("DELETE FROM Favorite_characters WHERE id = :id")
    suspend fun deleteCharacterById(id: Int)
}