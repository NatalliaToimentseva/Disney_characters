package com.example.disney_characters.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.repository.domain.CharactersResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

private const val TEMP_URL =
    "https://static.wikia.nocookie.net/disney/images/1/15/Arianna_Tangled.jpg/revision/latest?cb=20160715191802"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadCharactersUseCase: LoadCharactersUseCase
) : ViewModel() {

    val disneyCharactersList = MutableLiveData<List<CharacterItemModel>>(
        arrayListOf(
            CharacterItemModel(1, "Test1", TEMP_URL),
            CharacterItemModel(2, "Test2", TEMP_URL),
            CharacterItemModel(3, "Test3", TEMP_URL),
            CharacterItemModel(4, "Test4", TEMP_URL),
            CharacterItemModel(5, "Test5", TEMP_URL),
            CharacterItemModel(6, "Test6", TEMP_URL),
            CharacterItemModel(7, "Test7", TEMP_URL)
        )
    )
    val isInProgress = MutableLiveData(false)
    var showError: ((throwable: Throwable) -> Unit)? = null
    private val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun loadListData() {
        disposable.add(
            loadCharactersUseCase.loadAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isInProgress.value = true
                }
                .subscribe({ result ->
                    handleResult(result)
                }, { error ->
                    handleResult(CharactersResult.Error(error))
                }
                )
        )
    }

    private fun handleResult(result: CharactersResult) {
        when (result) {
            is CharactersResult.Success<*> -> {
                disneyCharactersList.value = result.data as List<CharacterItemModel>
                isInProgress.value = false
            }

            is CharactersResult.Error -> {
                result.throwable.let { showError?.invoke(it) }
                isInProgress.value = false
            }
        }
    }
}