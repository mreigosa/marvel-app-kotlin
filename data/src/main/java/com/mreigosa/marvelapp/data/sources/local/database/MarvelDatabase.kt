package com.mreigosa.marvelapp.data.sources.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mreigosa.marvelapp.data.sources.local.database.dao.MarvelCharacterDao
import com.mreigosa.marvelapp.data.sources.local.model.MarvelCharacterLocalEntity

@Database(
    version = 1,
    entities = [MarvelCharacterLocalEntity::class]
)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun characterDao(): MarvelCharacterDao

    companion object {
        fun buildDatabase(context: Context): MarvelDatabase = Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "MarvelCharacters.db"
        ).build()
    }
}