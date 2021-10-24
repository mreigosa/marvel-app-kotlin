package com.mreigosa.marvelapp.domain.repository

import com.mreigosa.marvelapp.domain.model.MarvelCharacter

interface MarvelRepository {

    fun getCharacters(offset: Int): Result<List<MarvelCharacter>>
}