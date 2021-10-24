package com.mreigosa.marvelapp

import android.app.Application
import com.mreigosa.marvelapp.data.di.remoteModule
import com.mreigosa.marvelapp.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApp)
            modules(
                remoteModule,
                repositoryModule
            )
        }
    }
}