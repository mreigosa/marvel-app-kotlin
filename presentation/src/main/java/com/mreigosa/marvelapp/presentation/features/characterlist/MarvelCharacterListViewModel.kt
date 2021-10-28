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

    private val _characters = MutableLiveData<CharacterListViewState>()
    val characters: LiveData<CharacterListViewState> = _characters

    init {
        _characters.value = CharacterListViewState.FirstLoading
        fetchCharacters()
    }

    private fun fetchCharacters(offset: Int = 0) {
        invoker.execute(
            viewModelScope,
            getCharactersUseCase withParams GetCharactersUseCase.Params(offset)
        ) { result ->
            result.fold(
                onSuccess = ::onCharactersRetrieved,
                onFailure = ::onError
            )
        }
    }

    private fun onCharactersRetrieved(characters: List<MarvelCharacter>) {
        _characters.value = CharacterListViewState.CharacterListLoaded(characters)
    }

    private fun onError(error: Throwable) {
        _characters.value = CharacterListViewState.Error
    }
}