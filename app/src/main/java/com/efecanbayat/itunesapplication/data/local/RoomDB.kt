package com.efecanbayat.itunesapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efecanbayat.itunesapplication.data.entity.RoomDataList

@Database(entities = [RoomDataList::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}