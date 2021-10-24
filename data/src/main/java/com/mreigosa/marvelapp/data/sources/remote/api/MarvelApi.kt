package com.mreigosa.marvelapp.data.sources.remote.api

import com.mreigosa.marvelapp.data.sources.remote.model.MarvelCharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
        const val API_KEY = "3561e583cbca0058ff07bc14cd2d58a5"
    }

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("offset") offset: Int
    ): Call<MarvelCharacterDataWrapper>
}