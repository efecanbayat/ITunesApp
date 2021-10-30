package com.efecanbayat.itunesapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.efecanbayat.itunesapplication.data.entity.DataList

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getFavoriteItems(): List<DataList>

    @Insert
    fun addFavoriteItem(item: DataList?)

    @Delete
    fun deleteFavoriteItem(item: DataList?)
}