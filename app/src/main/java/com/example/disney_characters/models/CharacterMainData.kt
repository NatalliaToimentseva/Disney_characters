package com.example.disney_characters.models

data class CharacterMainData (
    val name: String,
    val imgUrl: String?,
    val fields: List<CharacterFieldsModel>
)