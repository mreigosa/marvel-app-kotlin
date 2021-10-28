package com.mreigosa.marvelapp.data.sources.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mreigosa.marvelapp.data.sources.local.database.MarvelDatabase
import com.mreigosa.marvelapp.data.sources.local.database.dao.MarvelCharacterDao
import MockDataProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class MarvelCharacterDaoTest {

   private lateinit var sut: MarvelCharacterDao

   private lateinit var database: MarvelDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarvelDatabase::class.java
        ).build()

        sut = database.characterDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun given_characters_in_database_return_them() {
        val entities = givenCharacterEntities()
        sut.insert(entities)

        val dbEntities = sut.getCharacters(0)

        assertThat(dbEntities.size).isEqualTo(entities.size)
        for(e in entities){
            assertThat(dbEntities.contains(e)).isTrue
        }
    }

    @Test
    fun given_no_characters_in_database_return_empty_list() {
        val dbEntities = sut.getCharacters(0)

        assertThat(dbEntities).isEmpty()
    }

    @Test
    fun given_characters_in_database_when_delete_them_return_empty_list() {
        val entities = givenCharacterEntities()
        sut.insert(entities)

        sut.deleteAllCharacters()
        val dbEntities = sut.getCharacters(0)

        assertThat(dbEntities).isEmpty()
    }

    private fun givenCharacterEntities() = MockDataProvider.givenMarvelCharacterLocalEntityList()

}