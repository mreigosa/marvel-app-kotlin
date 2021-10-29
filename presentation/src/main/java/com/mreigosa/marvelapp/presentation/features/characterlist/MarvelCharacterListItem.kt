package com.mreigosa.marvelapp.presentation.features.characterlist

import com.mreigosa.marvelapp.domain.model.MarvelCharacter

enum class MarvelCharacterListItemType {
    ITEM_CONTENT, ITEM_LOADING
}

sealed class MarvelCharacterListItem {
    data class ContentItem(val character: MarvelCharacter) : MarvelCharacterListItem()
    object LoadingItem : MarvelCharacterListItem()
}