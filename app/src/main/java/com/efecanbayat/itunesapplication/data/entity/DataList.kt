package com.efecanbayat.itunesapplication.data.entity

import com.google.gson.annotations.SerializedName

data class DataList(
    @SerializedName("trackId")
    val trackId: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("longDescription")
    val longDescription: String?,
    @SerializedName("shortDescription")
    val shortDescription: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("collectionPrice")
    val collectionPrice: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("collectionName")
    val collectionName: String?
)
