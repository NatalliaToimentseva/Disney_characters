package com.example.disney_characters.dataSources.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.disney_characters.dataSources.roomDB.entities.CharacterDbEntity

@Database(
    version = 1,
    entities = [
        CharacterDbEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getCharactersDao(): CharactersDao
}