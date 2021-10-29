package com.mreigosa.marvelapp.presentation.features.characterlist

import androidx.recyclerview.widget.DiffUtil

object MarvelCharacterListItemDiffCallback : DiffUtil.ItemCallback<MarvelCharacterListItem>() {
    override fun areItemsTheSame(
        oldItem: MarvelCharacterListItem,
        newItem: MarvelCharacterListItem
    ): Boolean {
        return when {
            oldItem is MarvelCharacterListItem.ContentItem && newItem is MarvelCharacterListItem.ContentItem -> oldItem.character.id == newItem.character.id
            oldItem is MarvelCharacterListItem.LoadingItem && newItem is MarvelCharacterListItem.LoadingItem -> true
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: MarvelCharacterListItem,
        newItem: MarvelCharacterListItem
    ): Boolean {
        return oldItem == newItem
    }
}