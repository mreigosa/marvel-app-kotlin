package com.mreigosa.marvelapp.presentation.mock

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.mreigosa.marvelapp.domain.executor.DispatcherProvider
import com.mreigosa.marvelapp.domain.executor.Invoker
import com.mreigosa.marvelapp.domain.executor.UseCaseInvoker
import com.mreigosa.marvelapp.domain.usecase.GetCharactersUseCase
import com.mreigosa.marvelapp.presentation.features.characterlist.MarvelCharacterListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class BaseUiTest : KoinTest {

    private val dispatchersProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher = Dispatchers.Main
        override val background: CoroutineDispatcher = Dispatchers.Main
    }

    private var charactersUseCase: GetCharactersUseCase = mockk()

    @Before
    open fun setUp() {
        startKoin {
            modules(listOf(module {
                factory<Invoker> { UseCaseInvoker(dispatchersProvider) }
                viewModel { MarvelCharacterListViewModel(charactersUseCase, get()) }
            }))
        }
    }

    @After
    open fun tearDown() {
        stopKoin()
    }

    protected fun givenGetCharacterListSuccess() {
        coEvery { charactersUseCase.run() } returns Result.success(CharacterFactory.createMany(20))
        coEvery { charactersUseCase withParams any() } returns charactersUseCase
    }

    protected fun givenGetCharacterListError() {
        coEvery { charactersUseCase.run() } returns Result.failure(Throwable())
        coEvery { charactersUseCase withParams any() } returns charactersUseCase
    }

    protected fun givenEmptyCharactersRetrieved() {
        coEvery { charactersUseCase.run() } returns Result.success(listOf())
        coEvery { charactersUseCase withParams any() } returns charactersUseCase
    }
}