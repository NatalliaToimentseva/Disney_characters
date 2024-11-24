package com.example.disney_characters.ui.details.domain

import com.example.disney_characters.models.CharacterMainData

sealed class DetailsResult {

    data class Success(val data: CharacterMainData?) : DetailsResult()

    data class Error(val throwable: Throwable) : DetailsResult()
}