package com.mreigosa.marvelapp.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class EspressoRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, EspressoApp::class.java.name, context)
    }
}

class EspressoApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}