package com.mreigosa.marvelapp.data.sources.remote.api

import com.mreigosa.marvelapp.data.sources.remote.model.MarvelCharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
    }

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("offset") offset: Int
    ): Call<MarvelCharacterDataWrapper>
}