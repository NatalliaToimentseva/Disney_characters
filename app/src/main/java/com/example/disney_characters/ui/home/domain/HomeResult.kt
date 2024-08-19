package com.example.disney_characters.ui.home.domain

import com.example.disney_characters.models.CharacterItemModel

sealed class HomeResult {

    data class Success(val data: List<CharacterItemModel>) : HomeResult()

    data class Error(val throwable: Throwable) : HomeResult()
}