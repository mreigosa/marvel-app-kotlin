package com.mreigosa.marvelapp.domain.model

import java.io.Serializable

data class MarvelCharacter(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
): Serializable
