package com.mreigosa.marvelapp.domain.executor

interface IdleNotifier {
    fun increment(): Int

    @Throws(IllegalArgumentException::class)
    fun decrement()
}