package com.mreigosa.marvelapp.presentation.features.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
            when (it) {
                CharacterListViewState.FirstLoading -> showLoading()
                CharacterListViewState.Error -> showError()
                is CharacterListViewState.CharacterListLoaded -> onCharactersLoaded(it.characters)
            }
        }
    }

    private fun showLoading(){
        binding.progressBar.isVisible = true
    }

    private fun hideLoading(){
        binding.progressBar.isGone = true
    }

    private fun onCharactersLoaded(characters: List<MarvelCharacter>){
        hideLoading()
        charactersAdapter.submitList(characters)
    }

    private fun showError() {
        hideLoading()
        showSnackBar("Something went wrong")
    }

    private fun onCharacterSelected(character: MarvelCharacter) {
        showSnackBar("${character.name} selected")
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }
}