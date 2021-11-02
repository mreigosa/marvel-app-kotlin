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

    private val _viewState = MutableLiveData<CharacterListViewState>()
    val viewState: LiveData<CharacterListViewState> = _viewState

    private val characterList: MutableList<MarvelCharacter> = mutableListOf()

    init {
        _viewState.value = CharacterListViewState.FirstLoading
        fetchCharacters()
    }

    fun fetchCharacters(offset: Int = 0) {
        invoker.execute(
            viewModelScope,
            getCharactersUseCase withParams GetCharactersUseCase.Params(offset)
        ) { result ->
            result.fold(
                onSuccess = { characters -> onCharactersRetrieved(offset, characters) },
                onFailure = ::onError
            )
        }
    }

    private fun onCharactersRetrieved(offset: Int, characters: List<MarvelCharacter>) {
        if (characters.isEmpty() && offset == 0) {
            _viewState.value = CharacterListViewState.EmptyCharactersLoaded
        } else {
            characterList.addAll(characters)
            _viewState.value = CharacterListViewState.CharacterListLoaded(characterList)
        }
    }

    private fun onError(error: Throwable) {
        _viewState.value = CharacterListViewState.Error
    }
}