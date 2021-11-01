package com.mreigosa.marvelapp.presentation.features.characterlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.presentation.databinding.ItemLoadingBinding
import com.mreigosa.marvelapp.presentation.databinding.ItemMarvelCharacterBinding

sealed class MarvelCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    data class MarvelCharacterContentViewHolder(
        private val binding: ItemMarvelCharacterBinding
    ) : MarvelCharacterViewHolder(binding.root) {

        fun bind(character: MarvelCharacter, listener: (MarvelCharacter, View) -> Unit) {
            binding.itemCharacterName.text = character.name
            Glide.with(binding.root)
                .load(character.image)
                .into(binding.itemCharacterImage)

            binding.itemCharacterImage.transitionName = character.image

            binding.root.setOnClickListener {
                listener(character, binding.itemCharacterImage)
            }
        }
    }

    data class MarvelCharacterLoadingViewHolder(
        private val binding: ItemLoadingBinding
    ) : MarvelCharacterViewHolder(binding.root)
}