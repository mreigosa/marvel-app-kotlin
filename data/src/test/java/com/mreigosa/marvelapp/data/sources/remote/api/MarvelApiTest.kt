package com.mreigosa.marvelapp.data.sources.remote.api

import com.mreigosa.marvelapp.data.sources.remote.executeCall
import com.mreigosa.marvelapp.data.sources.remote.mock.BaseApiTest
import MockDataProvider.GET_CHARACTERS_RESPONSE_FILE
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.net.HttpURLConnection

class MarvelApiTest : BaseApiTest() {

    @Test
    fun `given success response, characters are retrieved`() {
        mockHttpResponse(mockServer, GET_CHARACTERS_RESPONSE_FILE, HttpURLConnection.HTTP_OK)

        val response = api.getCharacters(0).executeCall()

        assertThat(response).isNotNull
        with(response!!) {
            assertThat(HttpURLConnection.HTTP_OK).isEqualTo(code?.toInt())
            assertThat(data).isNotNull
            assertThat(data?.results).isNotNull
            assertThat(data?.results).isNotEmpty
        }
    }

    @Test(expected = Exception::class)
    fun `given server error, exception is thrown`() {
        mockHttpResponseCode(mockServer, HttpURLConnection.HTTP_INTERNAL_ERROR)

        api.getCharacters(0).executeCall()
    }
}