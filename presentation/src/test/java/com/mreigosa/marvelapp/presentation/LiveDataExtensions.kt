package com.mreigosa.marvelapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeForTesting(observer: Observer<T> = Observer {}, block: () -> Unit) {
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}