package com.mreigosa.marvelapp.presentation.features.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mreigosa.marvelapp.domain.executor.Invoker
import com.mreigosa.marvelapp.domain.executor.UseCaseInvoker
import com.mreigosa.marvelapp.domain.usecase.GetCharactersUseCase
import com.mreigosa.marvelapp.presentation.TestCoroutineRule
import com.mreigosa.marvelapp.presentation.mock.CharacterFactory
import com.mreigosa.marvelapp.presentation.mock.TestDispatcherProvider
import com.mreigosa.marvelapp.presentation.observeForTesting
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import java.lang.Exception

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MarvelCharacterListViewModelTest : KoinTest {

    private lateinit var sut: MarvelCharacterListViewModel

    private var charactersUseCase: GetCharactersUseCase = mockk()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    factory<Invoker> { UseCaseInvoker(TestDispatcherProvider()) }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `given view model, when it is created characters are retrieved`() = runBlockingTest {
        val mockCharacters = CharacterFactory.createMany(20)

        coEvery { charactersUseCase.run()} returns Result.success(mockCharacters)
        coEvery { charactersUseCase withParams any() } returns charactersUseCase

        val viewStateObserver = mockk<Observer<CharacterListViewState>>(relaxed = true)

        sut = MarvelCharacterListViewModel(charactersUseCase, get())

        coVerify {
            charactersUseCase.run()
        }

        sut.viewState.observeForTesting {
            viewStateObserver.onChanged(CharacterListViewState.FirstLoading)
            viewStateObserver.onChanged(CharacterListViewState.CharacterListLoaded(mockCharacters))
        }
    }

    @Test
    fun `given view model, when load characters, characters are retrieved`() = runBlockingTest {
        val mockCharacters = CharacterFactory.createMany(20)

        coEvery { charactersUseCase.run()} returns Result.success(mockCharacters)
        coEvery { charactersUseCase withParams any() } returns charactersUseCase

        val viewStateObserver = mockk<Observer<CharacterListViewState>>(relaxed = true)

        sut = MarvelCharacterListViewModel(charactersUseCase, get())
        sut.fetchCharacters(0)

        coVerify {
            charactersUseCase.run()
        }

        sut.viewState.observeForTesting {
            viewStateObserver.onChanged(CharacterListViewState.CharacterListLoaded(mockCharacters))
        }
    }

    @Test
    fun `given view model, when load characters, error is retrieved`() = runBlockingTest {
        coEvery { charactersUseCase.run()} returns Result.failure(Exception())
        coEvery { charactersUseCase withParams any() } returns charactersUseCase

        val viewStateObserver = mockk<Observer<CharacterListViewState>>(relaxed = true)

        sut = MarvelCharacterListViewModel(charactersUseCase, get())
        sut.fetchCharacters(0)

        coVerify {
            charactersUseCase.run()
        }

        sut.viewState.observeForTesting {
            viewStateObserver.onChanged(CharacterListViewState.Error)
        }
    }
}