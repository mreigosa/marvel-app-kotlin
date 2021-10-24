package com.mreigosa.marvelapp.domain.usecase

import com.mreigosa.marvelapp.domain.mock.CharacterFactory
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.domain.repository.MarvelRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    private lateinit var sut: GetCharactersUseCase

    private val repository = mockk<MarvelRepository>()

    @Before
    fun setUp() {
        sut = GetCharactersUseCase(repository)
    }

    @Test
    fun `get characters use case returns success result`() {
        coEvery { repository.getCharacters(any()) } returns givenCharacterList()

        runBlocking {
            val result = sut.withParams(GetCharactersUseCase.Params(0)).run()

            coVerify { repository.getCharacters(any()) }

            assertThat(result).isNotNull
            assertThat(result.isSuccess).isTrue
            assertThat(result.getOrNull()).isNotNull
            assertThat(result.getOrNull()).isNotEmpty
        }
    }

    @Test
    fun `get characters use case returns success empty result`() {
        coEvery { repository.getCharacters(any()) } returns givenEmptyCharacterList()

        runBlocking {
            val result = sut.withParams(GetCharactersUseCase.Params(0)).run()

            coVerify { repository.getCharacters(any()) }

            assertThat(result).isNotNull
            assertThat(result.isSuccess).isTrue
            assertThat(result.getOrNull()).isNotNull
            assertThat(result.getOrNull()).isEmpty()
        }
    }

    @Test
    fun `get characters use case returns error`() {
        coEvery { repository.getCharacters(any()) } returns givenError()

        runBlocking {
            val result = sut.withParams(GetCharactersUseCase.Params(0)).run()

            coVerify { repository.getCharacters(any()) }

            assertThat(result).isNotNull
            assertThat(result.isFailure).isTrue
        }
    }

    private fun givenCharacterList() = Result.success(CharacterFactory.createMany(20))
    private fun givenEmptyCharacterList() = Result.success(listOf<MarvelCharacter>())
    private fun givenError() = Result.failure<List<MarvelCharacter>>(Throwable())
}