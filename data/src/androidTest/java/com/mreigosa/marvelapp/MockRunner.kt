package com.mreigosa.marvelapp

import android.app.Application
import android.content.Context
import com.mreigosa.marvelapp.runner.MockApp
import androidx.test.runner.AndroidJUnitRunner

class MockRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MockApp::class.java.name, context)
    }

}