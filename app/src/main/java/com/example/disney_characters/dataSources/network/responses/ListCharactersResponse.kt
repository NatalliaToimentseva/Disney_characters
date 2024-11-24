package com.example.disney_characters.dataSources.network.responses

import com.google.gson.annotations.SerializedName

data class ListCharactersResponse(
    @SerializedName("data")
    val data: List<CharacterDataResponse>
)