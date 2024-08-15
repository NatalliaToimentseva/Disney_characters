package com.example.disney_characters.ui.details

import com.example.disney_characters.repository.DisneyCharactersListRepository
import com.example.disney_characters.repository.domain.CharactersResult
import com.example.disney_characters.utils.toCharacterMainData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoadCharacterByIdUseCase @Inject constructor(
    private val repository: DisneyCharactersListRepository
) {

    fun loadCharacterById(id: Int): Single<CharactersResult> {
        return repository.getCharacterById(id).map { data ->
            if (data.isSuccessful) {
                CharactersResult.Success(data.body()?.toCharacterMainData())
            } else {
                CharactersResult.Error(Throwable(data.message()))
            }
        }
    }
}