package com.mreigosa.marvelapp.presentation.features.characterlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.presentation.R
import com.mreigosa.marvelapp.presentation.databinding.ItemMarvelCharacterBinding

class MarvelCharacterAdapter(
    private val listener: (MarvelCharacter) -> Unit
) : ListAdapter<MarvelCharacter, MarvelCharacterAdapter.MarvelCharacterViewHolder>(
    MarvelCharacterDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_marvel_character, parent, false)
        return MarvelCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class MarvelCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMarvelCharacterBinding.bind(itemView)

        fun bind(character: MarvelCharacter, listener: (MarvelCharacter) -> Unit) {
            binding.itemCharacterName.text = character.name
            Glide.with(itemView.context)
                .load(character.image)
                .into(binding.itemCharacterImage)

            binding.root.setOnClickListener {
                listener(character)
            }
        }
    }

    object MarvelCharacterDiffCallback : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }
}