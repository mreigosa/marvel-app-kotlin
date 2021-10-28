package com.mreigosa.marvelapp.data.sources.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mreigosa.marvelapp.data.sources.local.model.MarvelCharacterLocalEntity

@Dao
interface MarvelCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<MarvelCharacterLocalEntity>)

    @Query("SELECT * FROM marvel_characters LIMIT 20 OFFSET :offset")
    fun getCharacters(offset: Int): List<MarvelCharacterLocalEntity>

    @Query("DELETE FROM marvel_characters")
    fun deleteAllCharacters()
}