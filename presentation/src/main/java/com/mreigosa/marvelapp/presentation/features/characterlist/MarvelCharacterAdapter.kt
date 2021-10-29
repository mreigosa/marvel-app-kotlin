package com.mreigosa.marvelapp.presentation.features.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.presentation.databinding.ItemLoadingBinding
import com.mreigosa.marvelapp.presentation.databinding.ItemMarvelCharacterBinding

class MarvelCharacterAdapter(
    private val listener: (MarvelCharacter) -> Unit
) : ListAdapter<MarvelCharacterListItem, MarvelCharacterViewHolder>(
    MarvelCharacterListItemDiffCallback
) {

    private var isLoading: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        return when (viewType) {
            MarvelCharacterListItemType.ITEM_CONTENT.ordinal -> createContentViewHolder(parent)
            else -> createLoadingViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        when (holder) {
            is MarvelCharacterViewHolder.MarvelCharacterContentViewHolder -> holder.bind(
                (getItem(position) as MarvelCharacterListItem.ContentItem).character,
                listener
            )
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is MarvelCharacterListItem.ContentItem -> MarvelCharacterListItemType.ITEM_CONTENT.ordinal
            else -> MarvelCharacterListItemType.ITEM_LOADING.ordinal
        }

    fun showLoading() {
        if (!isLoading) {
            isLoading = true
            currentList.toMutableList().add(MarvelCharacterListItem.LoadingItem)
            val newList = currentList.toMutableList()
            newList.add(itemCount, MarvelCharacterListItem.LoadingItem)
            submitList(newList)
            notifyItemInserted(itemCount)
        }
    }

    private fun hideLoading() {
        if (itemCount == 0) return

        isLoading = false

        val loadingPosition = itemCount - 1
        val currentList = currentList.toMutableList()
        currentList.removeAt(loadingPosition)
        submitList(currentList)
        notifyItemRemoved(loadingPosition)
    }

    fun setItems(characters: List<MarvelCharacter>) {
        hideLoading()
        submitList(characters.map {
            MarvelCharacterListItem.ContentItem(it)
        })
    }

    private fun createContentViewHolder(parent: ViewGroup) =
        MarvelCharacterViewHolder.MarvelCharacterContentViewHolder(
            ItemMarvelCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private fun createLoadingViewHolder(parent: ViewGroup) =
        MarvelCharacterViewHolder.MarvelCharacterLoadingViewHolder(
            ItemLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    val spanSize = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (getItemViewType(position)) {
                MarvelCharacterListItemType.ITEM_CONTENT.ordinal -> 1
                else -> 2
            }
        }
    }
}