package com.efecanbayat.itunesapplication.data.local

import com.efecanbayat.itunesapplication.data.entity.RoomDataList
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val itemDao: ItemDao
) {

    fun getFavoriteItems(): List<RoomDataList> {
        return itemDao.getFavoriteItems()
    }

    fun addFavoriteItem(item: RoomDataList) {
        itemDao.addFavoriteItem(item)
    }

    fun deleteFavoriteItem(item: RoomDataList) {
        itemDao.deleteFavoriteItem(item)
    }
}