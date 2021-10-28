package com.mreigosa.marvelapp.data.sources.local.datasource

import MockDataProvider
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mreigosa.marvelapp.data.sources.local.database.MarvelDatabase
import com.mreigosa.marvelapp.data.sources.local.mapper.MarvelCharacterLocalEntityMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class MarvelLocalDataSourceImplTest {

    private lateinit var sut: MarvelLocalDataSourceImpl

    private lateinit var database: MarvelDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarvelDatabase::class.java
        ).build()

        sut = MarvelLocalDataSourceImpl(database, MarvelCharacterLocalEntityMapper)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun given_characters_in_database_return_them() {
        val databaseEntities = givenCharacterEntities()
        database.characterDao().insert(databaseEntities)

        val characters = sut.getCharacters(0)

        assertThat(characters.isEmpty()).isFalse
    }

    @Test
    fun given_no_characters_in_database_return_empty_list() {
        val characters = sut.getCharacters(0)

        assertThat(characters.isEmpty()).isTrue
    }

    @Test
    fun characters_can_be_saved_into_database() {
        val characters = MockDataProvider.givenMarvelCharacterEntityList()

        sut.saveCharacters(characters)

        val dbResult = database.characterDao().getCharacters(0)
        assertThat(dbResult.isEmpty()).isFalse
    }

    private fun givenCharacterEntities() = MockDataProvider.givenMarvelCharacterLocalEntityList()
}