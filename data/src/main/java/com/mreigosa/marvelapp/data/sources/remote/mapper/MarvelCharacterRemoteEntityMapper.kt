package com.mreigosa.marvelapp.data.sources.remote.mapper

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.remote.model.MarvelCharacterRemoteEntity
import com.mreigosa.marvelapp.data.sources.remote.model.Thumbnail

object MarvelCharacterRemoteEntityMapper {

    fun mapFromRemote(remoteEntity: MarvelCharacterRemoteEntity): MarvelCharacterEntity =
        with(remoteEntity) {
            MarvelCharacterEntity(
                id = id ?: 0,
                name = name.orEmpty(),
                description = description.orEmpty(),
                image = thumbnail?.getImageUrl().orEmpty()
            )
        }

    private fun Thumbnail.getImageUrl() = "$path.$extension"
}