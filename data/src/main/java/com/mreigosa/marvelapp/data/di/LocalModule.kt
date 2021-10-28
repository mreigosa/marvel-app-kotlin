package com.mreigosa.marvelapp.data.di

import com.mreigosa.marvelapp.data.sources.datasource.MarvelLocalDataSource
import com.mreigosa.marvelapp.data.sources.local.database.MarvelDatabase
import com.mreigosa.marvelapp.data.sources.local.datasource.MarvelLocalDataSourceImpl
import com.mreigosa.marvelapp.data.sources.local.mapper.MarvelCharacterLocalEntityMapper
import org.koin.dsl.module

val localModule = module {

    single { MarvelDatabase.buildDatabase(get()) }

    factory<MarvelLocalDataSource> {
        MarvelLocalDataSourceImpl(
            get(),
            MarvelCharacterLocalEntityMapper
        )
    }
}