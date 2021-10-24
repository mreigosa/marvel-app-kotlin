package com.mreigosa.marvelapp.data.sources.remote.mock

import com.mreigosa.marvelapp.data.di.provideNetworkModule
import com.mreigosa.marvelapp.data.sources.remote.api.MarvelApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

abstract class BaseApiTest : KoinTest {

    protected lateinit var api: MarvelApi
    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        startKoin { modules(listOf(provideNetworkModule(mockServer.url("/").toString()))) }
        api = get()
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
        stopKoin()
    }

    fun mockHttpResponse(mockServer: MockWebServer, fileName: String, responseCode: Int): Unit =
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(MockDataProvider.readJsonAsString(fileName))
        )

    fun mockHttpResponseCode(mockServer: MockWebServer, responseCode: Int): Unit =
        mockServer.enqueue(
            MockResponse().setResponseCode(responseCode)
        )
}