package com.example.disney_characters.repository.domain


sealed class Result {

    data class Success<T>(val data: T) : Result()

    data class Error(val throwable: Throwable) : Result()
}