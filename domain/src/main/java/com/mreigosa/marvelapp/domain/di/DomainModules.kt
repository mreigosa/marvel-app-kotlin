package com.mreigosa.marvelapp.domain.di

import com.mreigosa.marvelapp.domain.executor.DispatcherProvider
import com.mreigosa.marvelapp.domain.executor.DispatcherProviderImpl
import com.mreigosa.marvelapp.domain.executor.Invoker
import com.mreigosa.marvelapp.domain.executor.UseCaseInvoker
import com.mreigosa.marvelapp.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module

object DomainModules {

    val useCaseModule = module {
        factory<Invoker> { UseCaseInvoker(get()) }
        single<DispatcherProvider> { DispatcherProviderImpl() }

        factory { GetCharactersUseCase(get()) }
    }
}