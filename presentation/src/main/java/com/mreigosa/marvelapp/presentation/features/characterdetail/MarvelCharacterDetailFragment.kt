package com.mreigosa.marvelapp.presentation.features.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mreigosa.marvelapp.presentation.databinding.FragmentMarvelCharacterDetailBinding

class MarvelCharacterDetailFragment : Fragment() {

    private var _binding: FragmentMarvelCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MarvelCharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarvelCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val character = args.character
        with(binding) {
            Glide.with(binding.root)
                .load(character.image)
                .into(binding.characterDetailImage)

            characterDetailName.text = character.name
            characterDetailDescription.text = character.description
        }
    }
}