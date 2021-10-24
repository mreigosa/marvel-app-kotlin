package com.mreigosa.marvelapp.data.repository

import com.mreigosa.marvelapp.data.mapper.MarvelCharacterMapper
import com.mreigosa.marvelapp.data.sources.datasource.MarvelRemoteDataSource
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.domain.repository.MarvelRepository

class MarvelRepositoryImpl(
    private val remoteDataSource: MarvelRemoteDataSource,
    private val marvelCharacterMapper: MarvelCharacterMapper,
) : MarvelRepository {

    override fun getCharacters(offset: Int): Result<List<MarvelCharacter>> =
        try {
            val remoteResponse = remoteDataSource.getCharacters(offset)
            Result.success(
                remoteResponse.map { marvelCharacterMapper.mapFromEntity(it) }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
}