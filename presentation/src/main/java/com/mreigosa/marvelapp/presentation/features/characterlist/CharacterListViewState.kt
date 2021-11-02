package com.mreigosa.marvelapp.presentation.features.characterlist

import com.mreigosa.marvelapp.domain.model.MarvelCharacter

sealed class CharacterListViewState {
    object FirstLoading : CharacterListViewState()
    object Error : CharacterListViewState()
    data class CharacterListLoaded(val characters: List<MarvelCharacter>) : CharacterListViewState()
    object EmptyCharactersLoaded : CharacterListViewState()
}