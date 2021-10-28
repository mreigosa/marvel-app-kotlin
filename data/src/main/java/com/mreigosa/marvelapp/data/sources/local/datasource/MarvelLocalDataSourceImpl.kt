package com.mreigosa.marvelapp.data.sources.local.datasource

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.datasource.MarvelLocalDataSource
import com.mreigosa.marvelapp.data.sources.local.database.MarvelDatabase
import com.mreigosa.marvelapp.data.sources.local.mapper.MarvelCharacterLocalEntityMapper

class MarvelLocalDataSourceImpl(
    private val database: MarvelDatabase,
    private val characterMapper: MarvelCharacterLocalEntityMapper
) : MarvelLocalDataSource {

    override fun getCharacters(offset: Int): List<MarvelCharacterEntity> {
        val data = database.characterDao().getCharacters(offset)
        return data.map { characterMapper.mapFromLocal(it) }
    }

    override fun saveCharacters(characters: List<MarvelCharacterEntity>) {
        database.characterDao().insert(characters.map { characterMapper.mapToLocal(it) })
    }
}