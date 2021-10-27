package com.mreigosa.marvelapp.data.sources.remote.api

import com.mreigosa.marvelapp.data.BuildConfig
import com.mreigosa.marvelapp.data.sources.remote.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class MarvelHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttUrl = originalRequest.url

        val apiKey = BuildConfig.MARVEL_API_KEY
        val apiSecret = BuildConfig.MARVEL_API_SECRET
        val timestamp = Date().time.toString()
        val hash = "$timestamp$apiSecret$apiKey".md5()

        val url = originalHttUrl.newBuilder()
            .addQueryParameter(QUERY_PARAM_API_KEY, apiKey)
            .addQueryParameter(QUERY_PARAM_TIMESTAMP, timestamp)
            .addQueryParameter(QUERY_PARAM_HASH, hash)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        const val QUERY_PARAM_API_KEY = "apikey"
        const val QUERY_PARAM_TIMESTAMP = "ts"
        const val QUERY_PARAM_HASH = "hash"
    }
}