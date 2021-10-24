package com.mreigosa.marvelapp.data.sources.remote.mock

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object MockDataProvider {

    private val gson = GsonBuilder().create()

    const val GET_CHARACTERS_RESPONSE_FILE = "getCharactersApiResponse.json"
    const val MOCK_EMPTY_CHARACTERS_JSON_FILENAME = "getCharactersEmptyApiResponse.json"

    fun readJsonAsString(path: String): String {
        val stream = this.javaClass.classLoader?.getResourceAsStream(path) ?: return ""
        return stream.bufferedReader().use { bufferReader ->
            bufferReader.readText()
        }
    }

    private inline fun <reified T> parseJsonFile(jsonPath: String): T {
        val jsonAsString = readJsonAsString(jsonPath)
        return gson.fromJson(jsonAsString, object : TypeToken<T>() {}.type)
    }
}