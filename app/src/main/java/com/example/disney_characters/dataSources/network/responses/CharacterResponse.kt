package com.example.disney_characters.dataSources.network.responses

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    val data: HashMap<String, Any>
)