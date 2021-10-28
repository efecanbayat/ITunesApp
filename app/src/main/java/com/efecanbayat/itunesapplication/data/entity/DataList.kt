package com.efecanbayat.itunesapplication.data.entity

import com.google.gson.annotations.SerializedName

data class DataList(
    @SerializedName("artistId")
    val artistId: Int?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String?,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String?,
    @SerializedName("artworkUrl600")
    val artworkUrl600: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int?,
    @SerializedName("collectionArtistName")
    val collectionArtistName: String?,
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String?,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String?,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String?,
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double?,
    @SerializedName("collectionId")
    val collectionId: Int?,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("collectionPrice")
    val collectionPrice: Double?,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String?,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("discCount")
    val discCount: Int?,
    @SerializedName("discNumber")
    val discNumber: Int?,
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean?,
    @SerializedName("isStreamable")
    val isStreamable: Boolean?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("longDescription")
    val longDescription: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("shortDescription")
    val shortDescription: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("averageUserRating")
    val averageUserRating: Double?,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String?,
    @SerializedName("trackCount")
    val trackCount: Int?,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String?,
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double?,
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double?,
    @SerializedName("trackId")
    val trackId: Int?,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("formattedPrice")
    val formattedPrice: String?,
    @SerializedName("feedUrl")
    val feedUrl: String?,
    @SerializedName("trackNumber")
    val trackNumber: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double?,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int?,
    @SerializedName("userRatingCount")
    val userRatingCount: Int?,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String?,
    @SerializedName("wrapperType")
    val wrapperType: String?,
    @SerializedName("fileSizeBytes")
    val fileSizeBytes: Long?,
    @SerializedName("genreIds")
    val genreIds: ArrayList<String>?,
    @SerializedName("genres")
    val genres: ArrayList<String>?,
    @SerializedName("artistIds")
    val artistIds: ArrayList<Long>?
)
