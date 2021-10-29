package com.mreigosa.marvelapp.presentation.mock

import com.mreigosa.marvelapp.domain.model.MarvelCharacter

object CharacterFactory {
    fun createOne(): MarvelCharacter = givenMarvelCharacter()

    fun createMany(amount: Int): List<MarvelCharacter> {
        val list: MutableList<MarvelCharacter> = mutableListOf()
        for (i in 0 until amount) {
            list.add(givenMarvelCharacter(i.toString()))
        }
        return list
    }

    private fun givenMarvelCharacter(id: String = "1") = MarvelCharacter(
        id = id,
        name = "Marvel Hero",
        description = "marvel character description",
        image = "http://i.annihil.us/u/prod/marvel/i/mg/3/80/4c00358ec7548.jpg",
    )
}