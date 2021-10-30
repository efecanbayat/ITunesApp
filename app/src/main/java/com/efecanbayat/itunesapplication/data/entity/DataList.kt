package com.efecanbayat.itunesapplication.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "item")
@Parcelize
data class DataList(
    @PrimaryKey()
    @SerializedName("trackId")
    @ColumnInfo(name = "trackId")
    val trackId: String,

    @SerializedName("artworkUrl100")
    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100: String?,

    @SerializedName("trackName")
    @ColumnInfo(name = "trackName")
    val trackName: String?,

    @SerializedName("longDescription")
    @ColumnInfo(name = "longDescription")
    val longDescription: String?,

    @SerializedName("shortDescription")
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @SerializedName("kind")
    @ColumnInfo(name = "kind")
    val kind: String?,

    @SerializedName("releaseDate")
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,

    @SerializedName("collectionPrice")
    @ColumnInfo(name = "collectionPrice")
    val collectionPrice: String?,

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: String?,

    @SerializedName("collectionName")
    @ColumnInfo(name = "collectionName")
    val collectionName: String?
) : Parcelable
