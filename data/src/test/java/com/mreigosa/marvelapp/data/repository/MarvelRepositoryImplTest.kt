package com.mreigosa.marvelapp.data.repository

import com.mreigosa.marvelapp.data.mapper.MarvelCharacterMapper
import com.mreigosa.marvelapp.data.sources.datasource.MarvelRemoteDataSource
import MockDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImplTest {

    private lateinit var sut: MarvelRepositoryImpl

    private val remoteDataSource = mockk<MarvelRemoteDataSource>()

    @Before
    fun setUp() {
        sut = MarvelRepositoryImpl(remoteDataSource, MarvelCharacterMapper)
    }

    @Test
    fun `given marvel repository, when retrieve characters, then success result`() {
        coEvery { remoteDataSource.getCharacters(any()) } returns MockDataProvider.givenMarvelCharacterEntityList()

        val result = sut.getCharacters(0)

        coVerify {
            remoteDataSource.getCharacters(any())
        }

        assertThat(result.isSuccess).isTrue
        assertThat((result.getOrNull())).isNotNull
        assertThat((result.getOrNull())).isNotEmpty
    }
}