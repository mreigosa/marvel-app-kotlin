package com.mreigosa.marvelapp.presentation.features.characterdetail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.transition.MaterialContainerTransform
import com.mreigosa.marvelapp.presentation.R
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

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 200L
            scrimColor = Color.TRANSPARENT
        }

        postponeEnterTransition()

        initView()
    }

    private fun initView() {
        val character = args.character
        with(binding) {
            characterDetailImage.transitionName = character.image
            Glide.with(binding.root)
                .load(character.image)
                .listener(imageLoaderListener)
                .into(binding.characterDetailImage)

            characterDetailName.text = character.name
            characterDetailDescription.text = character.description
        }
    }

    private val imageLoaderListener = object : RequestListener<Drawable>{
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadFinished()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadFinished()
            return false
        }
    }

    private fun onLoadFinished(){
        binding.progressBar.isGone = true
        startPostponedEnterTransition()
    }
}