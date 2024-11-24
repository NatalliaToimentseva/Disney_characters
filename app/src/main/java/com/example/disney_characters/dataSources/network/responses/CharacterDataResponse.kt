package com.example.disney_characters.dataSources.network.responses

import com.google.gson.annotations.SerializedName

data class CharacterDataResponse(
    @SerializedName("_id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)