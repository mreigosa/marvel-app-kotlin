package com.mreigosa.marvelapp.data.sources.local.mapper

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.local.model.MarvelCharacterLocalEntity

object MarvelCharacterLocalEntityMapper {

    fun mapFromLocal(localEntity: MarvelCharacterLocalEntity): MarvelCharacterEntity =
        with(localEntity) {
            MarvelCharacterEntity(
                id = id,
                name = name,
                description = description,
                image = image
            )
        }

    fun mapToLocal(dataEntity: MarvelCharacterEntity): MarvelCharacterLocalEntity =
        with(dataEntity) {
            MarvelCharacterLocalEntity(
                id = id,
                name = name,
                description = description,
                image = image
            )
        }
}