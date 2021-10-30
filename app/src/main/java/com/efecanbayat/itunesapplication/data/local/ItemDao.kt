package com.efecanbayat.itunesapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.data.entity.RoomDataList

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getFavoriteItems(): List<RoomDataList>

    @Insert
    fun addFavoriteItem(item: RoomDataList?)

    @Delete
    fun deleteFavoriteItem(item: RoomDataList?)
}