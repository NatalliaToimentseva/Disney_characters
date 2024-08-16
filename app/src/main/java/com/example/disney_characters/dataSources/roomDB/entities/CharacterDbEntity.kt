package com.example.disney_characters.dataSources.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Favorite_characters",
    indices = [
        Index("id", unique = true)
    ]
)
data class CharacterDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("db_id")
    val dbId: Int,
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("image")
    val imageUrl: String?
)