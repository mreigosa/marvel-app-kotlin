package com.mreigosa.marvelapp.data.sources.datasource

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity

interface MarvelLocalDataSource {
    fun getCharacters(offset: Int): List<MarvelCharacterEntity>
    fun saveCharacters(characters: List<MarvelCharacterEntity>)
}