package com.example.disney_characters.repository.domain


sealed class CharactersResult {

    data class Success<T>(val data: T) : CharactersResult()

    data class Error(val throwable: Throwable) : CharactersResult()
}