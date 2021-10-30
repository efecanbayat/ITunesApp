package com.efecanbayat.itunesapplication.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "item")
@Parcelize
data class RoomDataList(
    @PrimaryKey()
    @ColumnInfo(name = "trackId") val trackId: String,
    @ColumnInfo(name = "artworkUrl100") val artworkUrl100: String?,
    @ColumnInfo(name = "trackName") val trackName: String?,
    @ColumnInfo(name = "longDescription") val longDescription: String?,
    @ColumnInfo(name = "shortDescription") val shortDescription: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "kind") val kind: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "collectionPrice") val collectionPrice: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "collectionName") val collectionName: String?
) : Parcelable
