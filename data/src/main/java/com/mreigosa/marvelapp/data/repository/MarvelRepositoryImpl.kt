package com.mreigosa.marvelapp.data.repository

import com.mreigosa.marvelapp.data.mapper.MarvelCharacterMapper
import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.datasource.MarvelLocalDataSource
import com.mreigosa.marvelapp.data.sources.datasource.MarvelRemoteDataSource
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.domain.repository.MarvelRepository

class MarvelRepositoryImpl(
    private val remoteDataSource: MarvelRemoteDataSource,
    private val localDataSource: MarvelLocalDataSource,
    private val marvelCharacterMapper: MarvelCharacterMapper,
) : MarvelRepository {

    override fun getCharacters(offset: Int): Result<List<MarvelCharacter>> =
        try {
            val localData = localDataSource.getCharacters(offset)

            val characters = if (localData.isEmpty()) {
                getCharactersFromRemote(offset)
            } else {
                localData
            }

            Result.success(
                characters.map { marvelCharacterMapper.mapFromEntity(it) }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }


    private fun getCharactersFromRemote(offset: Int): List<MarvelCharacterEntity> {
        val characters = remoteDataSource.getCharacters(offset)
        localDataSource.saveCharacters(characters)
        return characters
    }
}