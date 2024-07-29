package com.example.disney_characters.ui.models

data class CharacterMainData (
    val name: String,
    val imgUrl: String?,
    val fields: List<CharacterFieldsModel>
)