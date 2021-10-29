package com.mreigosa.marvelapp.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class MarvelCharacterDataWrapper(
    @SerializedName("attributionHTML") val attributionHTML: String?,
    @SerializedName("attributionText") val attributionText: String?,
    @SerializedName("code") val code: String?,
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("data") val data: MarvelCharacterDataContainer?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("status") val status: String?
)

data class MarvelCharacterDataContainer(
    @SerializedName("count") val count: String?,
    @SerializedName("limit") val limit: String?,
    @SerializedName("offset") val offset: String?,
    @SerializedName("results") val results: List<MarvelCharacterRemoteEntity>?,
    @SerializedName("total") val total: String?
)

data class MarvelCharacterRemoteEntity(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
)

data class Thumbnail(
    @SerializedName("extension") val extension: String?,
    @SerializedName("path") val path: String?
)