package com.mreigosa.marvelapp.data.sources.remote.datasource

import com.mreigosa.marvelapp.data.sources.remote.mapper.MarvelCharacterRemoteEntityMapper
import com.mreigosa.marvelapp.data.sources.remote.mock.BaseApiTest
import MockDataProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

class MarvelRemoteDataSourceImplTest : BaseApiTest() {

    private lateinit var sut: MarvelRemoteDataSourceImpl

    @Before
    fun before() {
        sut = MarvelRemoteDataSourceImpl(api, MarvelCharacterRemoteEntityMapper)
    }

    @Test
    fun `given success response, characters retrieved`() {
        mockHttpResponse(
            mockServer,
            MockDataProvider.GET_CHARACTERS_RESPONSE_FILE,
            HttpURLConnection.HTTP_OK
        )

        val response = sut.getCharacters(0)

        assertThat(response).isNotEmpty
    }

    @Test
    fun `given empty success response, empty list retrieved`() {
        mockHttpResponse(
            mockServer,
            MockDataProvider.MOCK_EMPTY_CHARACTERS_JSON_FILENAME,
            HttpURLConnection.HTTP_OK
        )

        val response = sut.getCharacters(0)

        assertThat(response).isEmpty()
    }

    @Test(expected = Exception::class)
    fun `given error response, exception is thrown`() {
        mockHttpResponseCode(mockServer, HttpURLConnection.HTTP_INTERNAL_ERROR)

        sut.getCharacters(0)
    }
}