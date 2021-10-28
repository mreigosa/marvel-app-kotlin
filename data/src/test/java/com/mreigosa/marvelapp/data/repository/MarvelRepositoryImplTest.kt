package com.mreigosa.marvelapp.data.repository

import MockDataProvider
import com.mreigosa.marvelapp.data.mapper.MarvelCharacterMapper
import com.mreigosa.marvelapp.data.sources.datasource.MarvelLocalDataSource
import com.mreigosa.marvelapp.data.sources.datasource.MarvelRemoteDataSource
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImplTest {

    private lateinit var sut: MarvelRepositoryImpl

    private val remoteDataSource = mockk<MarvelRemoteDataSource>()
    private val localDataSource = mockk<MarvelLocalDataSource>()

    @Before
    fun setUp() {
        sut = MarvelRepositoryImpl(remoteDataSource, localDataSource, MarvelCharacterMapper)
    }

    @Test
    fun `given no local data, when retrieve characters, then return remote data`() {
        coEvery { localDataSource.getCharacters(any()) } returns listOf()
        coEvery { localDataSource.saveCharacters(any()) } returns Unit
        coEvery { remoteDataSource.getCharacters(any()) } returns MockDataProvider.givenMarvelCharacterEntityList()

        val result = sut.getCharacters(0)

        coVerifyOrder {
            localDataSource.getCharacters(any())
            remoteDataSource.getCharacters(any())
            localDataSource.saveCharacters(any())
        }

        assertThat(result.isSuccess).isTrue
        assertThat((result.getOrNull())).isNotNull
        assertThat((result.getOrNull())).isNotEmpty
    }

    @Test
    fun `given local data, when retrieve characters, then return local data`() {
        coEvery { localDataSource.getCharacters(any()) } returns MockDataProvider.givenMarvelCharacterEntityList()

        val result = sut.getCharacters(0)

        coVerify {
            localDataSource.getCharacters(any())
        }

        coVerify(exactly = 0){
            remoteDataSource.getCharacters(any())
            localDataSource.saveCharacters(any())
        }

        assertThat(result.isSuccess).isTrue
        assertThat((result.getOrNull())).isNotNull
        assertThat((result.getOrNull())).isNotEmpty
    }

    @Test
    fun `when exception is thrown,return error`() {
        val exception = Exception("Error")
        coEvery { localDataSource.getCharacters(any()) } returns listOf()
        coEvery { remoteDataSource.getCharacters(any()) } throws exception


        val result = sut.getCharacters(0)

        coVerify {
            localDataSource.getCharacters(any())
            remoteDataSource.getCharacters(any())
        }

        assertThat(result.isFailure).isTrue
    }
}