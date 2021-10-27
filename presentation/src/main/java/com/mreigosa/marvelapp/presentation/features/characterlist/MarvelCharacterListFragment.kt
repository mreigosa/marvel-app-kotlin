package com.mreigosa.marvelapp.presentation.features.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.presentation.databinding.FragmentMarvelCharacterListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelCharacterListFragment : Fragment() {

    private val viewModel: MarvelCharacterListViewModel by viewModel()

    private var _binding: FragmentMarvelCharacterListBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter by lazy {
        MarvelCharacterAdapter(listener = ::onCharacterSelected)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarvelCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.characterList.apply {
            adapter = charactersAdapter
        }
    }

    private fun initObservers() {
        viewModel.characters.observe(viewLifecycleOwner) {
            charactersAdapter.submitList(it)
        }
    }

    private fun onCharacterSelected(character: MarvelCharacter) {
        Snackbar.make(
            binding.root,
            "${character.name} selected",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}