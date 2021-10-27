package com.mreigosa.marvelapp.data.sources.remote

import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

fun String.forceHttps(): String {
    return if (this.contains("https")) {
        this
    } else {
        this.replace("http", "https")
    }
}
