package com.mreigosa.marvelapp.data.sources.remote.api

import com.mreigosa.marvelapp.data.BuildConfig
import com.mreigosa.marvelapp.data.sources.remote.executeCall
import com.mreigosa.marvelapp.data.sources.remote.md5
import com.mreigosa.marvelapp.data.sources.remote.mock.BaseApiTest
import MockDataProvider.GET_CHARACTERS_RESPONSE_FILE
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.net.HttpURLConnection

class MarvelHeaderInterceptorTest : BaseApiTest() {

    @Test
    fun `that interceptor adds expected query parameters`() {
        mockHttpResponse(mockServer, GET_CHARACTERS_RESPONSE_FILE, HttpURLConnection.HTTP_OK)

        api.getCharacters(0).executeCall()

        val url = mockServer.takeRequest().requestUrl

        assertThat(url).isNotNull
        assertThat(url!!.queryParameter("apikey")).isEqualTo(BuildConfig.MARVEL_API_KEY)

        val timestamp = url.queryParameter("ts")
        assertThat(timestamp).isNotEmpty

        val expectedHash =
            "$timestamp${BuildConfig.MARVEL_API_SECRET}${BuildConfig.MARVEL_API_KEY}".md5()

        val hash = url.queryParameter("hash")
        assertThat(hash).isNotEmpty
        assertThat(hash).isEqualTo(expectedHash)
    }
}