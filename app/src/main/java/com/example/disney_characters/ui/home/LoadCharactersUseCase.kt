package com.example.disney_characters.ui.home

import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.domain.CharactersResult
import com.example.disney_characters.utils.toListCharacterModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoadCharactersUseCase @Inject constructor(
    private val repository: DisneyCharactersListRepository
) {

    fun loadAllCharacters(): Observable<CharactersResult> {
        return repository.getListCharacters().map { data ->
            if (data.isSuccessful) {
                CharactersResult.Success(data.body()?.toListCharacterModel() ?: arrayListOf())
            } else {
                CharactersResult.Error(Throwable(data.message()))
            }
        }
    }
}