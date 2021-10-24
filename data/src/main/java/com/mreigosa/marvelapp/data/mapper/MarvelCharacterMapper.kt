package com.mreigosa.marvelapp.data.mapper

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.domain.model.MarvelCharacter

object MarvelCharacterMapper {

    fun mapFromEntity(dataEntity: MarvelCharacterEntity): MarvelCharacter =
        with(dataEntity){
            return MarvelCharacter(id, name, description, image)
        }
}