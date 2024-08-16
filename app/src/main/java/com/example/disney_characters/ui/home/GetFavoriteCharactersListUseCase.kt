package com.example.disney_characters.ui.home

import android.database.sqlite.SQLiteConstraintException
import com.example.disney_characters.repository.CharactersDbRepository
import com.example.disney_characters.ui.home.domain.HomeResult
import com.example.disney_characters.utils.toCharacterItemModelList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteCharactersListUseCase @Inject constructor(
    private val charactersDbRepository: CharactersDbRepository
) {

    fun loadCharactersFavoriteList(): Flow<HomeResult> {
        return try {
            charactersDbRepository.getFavoriteCharacters().map {
                val list = it.toCharacterItemModelList()
                HomeResult.Success(list)
            }
        } catch (e: SQLiteConstraintException) {
            flow {
                emit(HomeResult.Error(Throwable(e.message)))
            }
        }
    }
}