package com.example.disney_characters.dataSources.network.entities

import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("_id")
    val id: Int,
    val imageUrl: String,
    val name: String
)