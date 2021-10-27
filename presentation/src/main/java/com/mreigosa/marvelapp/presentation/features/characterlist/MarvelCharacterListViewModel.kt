package com.mreigosa.marvelapp.presentation.features.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mreigosa.marvelapp.domain.executor.Invoker
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.domain.usecase.GetCharactersUseCase

class MarvelCharacterListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val invoker: Invoker
) : ViewModel() {

    private val _characters = MutableLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>> = _characters

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        invoker.execute(
            viewModelScope,
            getCharactersUseCase withParams GetCharactersUseCase.Params(0)
        ) { result ->
            result.fold(
                onSuccess = ::onCharactersRetrieved,
                onFailure = ::onError
            )
        }
    }

    private fun onCharactersRetrieved(characters: List<MarvelCharacter>) {
        _characters.value = characters
    }

    private fun onError(error: Throwable) {
    }
}