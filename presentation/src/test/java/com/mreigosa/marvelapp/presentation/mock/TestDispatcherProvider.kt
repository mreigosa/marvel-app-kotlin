package com.mreigosa.marvelapp.presentation.mock

import com.mreigosa.marvelapp.domain.executor.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val background: CoroutineDispatcher = Dispatchers.IO
}