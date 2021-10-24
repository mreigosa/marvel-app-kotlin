package com.mreigosa.marvelapp.data.sources.remote.datasource

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.datasource.MarvelRemoteDataSource
import com.mreigosa.marvelapp.data.sources.remote.api.MarvelApi
import com.mreigosa.marvelapp.data.sources.remote.executeCall
import com.mreigosa.marvelapp.data.sources.remote.mapper.MarvelCharacterRemoteEntityMapper

class MarvelRemoteDataSourceImpl(
    private val api: MarvelApi,
    private val characterMapper: MarvelCharacterRemoteEntityMapper
) : MarvelRemoteDataSource {

    override fun getCharacters(offset: Int): List<MarvelCharacterEntity> {
        val response = api.getCharacters(MarvelApi.API_KEY, offset).executeCall()
        return response?.data?.results?.let {
            it.map { marvelRemoteEntity -> characterMapper.mapFromRemote(marvelRemoteEntity) }
        } ?: throw Exception()
    }
}