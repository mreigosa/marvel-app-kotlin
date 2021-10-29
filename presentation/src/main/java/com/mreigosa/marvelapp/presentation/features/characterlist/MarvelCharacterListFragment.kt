package com.mreigosa.marvelapp.presentation.features.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.presentation.databinding.FragmentMarvelCharacterListBinding
import com.mreigosa.marvelapp.presentation.util.EndlessScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarvelCharacterListFragment : Fragment() {

    private val viewModel: MarvelCharacterListViewModel by viewModel()

    private var _binding: FragmentMarvelCharacterListBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter by lazy {
        MarvelCharacterAdapter(listener = ::onCharacterSelected)
    }

    private val endlessScrollListener by lazy {
        EndlessScrollListener { totalItems ->
            charactersAdapter.showLoading()
            viewModel.fetchCharacters(totalItems)
        }
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
            layoutManager = GridLayoutManager(context, 2).also {
                it.spanSizeLookup = charactersAdapter.spanSize
            }
            addOnScrollListener(endlessScrollListener)
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                CharacterListViewState.FirstLoading -> showLoading()
                CharacterListViewState.Error -> showError()
                is CharacterListViewState.CharacterListLoaded -> onCharactersLoaded(it.characters)
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isGone = true
    }

    private fun onCharactersLoaded(characters: List<MarvelCharacter>) {
        hideLoading()
        endlessScrollListener.setLoaded()
        charactersAdapter.setItems(characters)
    }

    private fun showError() {
        hideLoading()
        showSnackBar("Something went wrong")
    }

    private fun onCharacterSelected(character: MarvelCharacter) {
        findNavController().navigate(
            MarvelCharacterListFragmentDirections.actionToCharacterDetail(character)
        )
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }
}