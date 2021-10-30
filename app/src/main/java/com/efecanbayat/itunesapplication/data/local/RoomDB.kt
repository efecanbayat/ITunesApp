package com.efecanbayat.itunesapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efecanbayat.itunesapplication.data.entity.DataList

@Database(entities = [DataList::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}