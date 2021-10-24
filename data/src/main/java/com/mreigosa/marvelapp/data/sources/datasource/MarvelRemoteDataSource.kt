package com.mreigosa.marvelapp.data.sources.datasource

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity

interface MarvelRemoteDataSource {
    fun getCharacters(offset: Int): List<MarvelCharacterEntity>
}