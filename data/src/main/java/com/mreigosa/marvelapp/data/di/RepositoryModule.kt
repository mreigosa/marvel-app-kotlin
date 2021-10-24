package com.mreigosa.marvelapp.data.di

import com.mreigosa.marvelapp.data.mapper.MarvelCharacterMapper
import com.mreigosa.marvelapp.data.repository.MarvelRepositoryImpl
import com.mreigosa.marvelapp.domain.repository.MarvelRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MarvelRepository> { MarvelRepositoryImpl(get(), MarvelCharacterMapper) }
}