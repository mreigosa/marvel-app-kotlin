package com.mreigosa.marvelapp.presentation.di

import com.mreigosa.marvelapp.presentation.features.characterlist.MarvelCharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MarvelCharacterListViewModel(get(), get()) }
}