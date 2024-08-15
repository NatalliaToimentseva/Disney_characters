package com.example.disney_characters.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.disney_characters.models.CharacterMainData
import com.example.disney_characters.repository.domain.CharactersResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val loadCharacterByIdUseCase: LoadCharacterByIdUseCase
) : ViewModel() {

    val character = PublishSubject.create<CharacterMainData>()
    val isInProgress = MutableLiveData(false)
    var showError: ((throwable: Throwable) -> Unit)? = null
    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getCharacter(id: Int) {
        disposable.add(
            loadCharacterByIdUseCase.loadCharacterById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isInProgress.value = true
                }
                .subscribe({ result ->
                    handleResult(result)
                }, { error ->
                    handleResult(CharactersResult.Error(error))
                })
        )
    }

    private fun handleResult(result: CharactersResult) {
        when (result) {
            is CharactersResult.Success<*> -> {
                character.onNext(result.data as CharacterMainData)
                isInProgress.value = false
            }

            is CharactersResult.Error -> {
                result.throwable.let { showError?.invoke(it) }
                isInProgress.value = false
            }
        }
    }
}