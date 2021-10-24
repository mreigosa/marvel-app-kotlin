package com.mreigosa.marvelapp.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mreigosa.marvelapp.data.sources.datasource.MarvelRemoteDataSource
import com.mreigosa.marvelapp.data.sources.remote.api.MarvelApi
import com.mreigosa.marvelapp.data.sources.remote.datasource.MarvelRemoteDataSourceImpl
import com.mreigosa.marvelapp.data.sources.remote.mapper.MarvelCharacterRemoteEntityMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {
    factory<MarvelRemoteDataSource> {
        MarvelRemoteDataSourceImpl(get(), MarvelCharacterRemoteEntityMapper)
    }

    provideNetworkModule(MarvelApi.BASE_URL)
}

fun provideNetworkModule(baseUrl: String) = module {
    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), baseUrl) }
    single { provideMarvelApi(get()) }
}

private fun provideGson(): Gson = GsonBuilder().create()

private fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideMarvelApi(retrofit: Retrofit): MarvelApi =
    retrofit.create(MarvelApi::class.java)