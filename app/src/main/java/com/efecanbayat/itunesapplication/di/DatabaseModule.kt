package com.efecanbayat.itunesapplication.di

import android.content.Context
import androidx.room.Room
import com.efecanbayat.itunesapplication.data.local.ItemDao
import com.efecanbayat.itunesapplication.data.local.LocalDataSource
import com.efecanbayat.itunesapplication.data.local.RoomDB
import com.efecanbayat.itunesapplication.data.local.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(
        sharedPrefManager: SharedPrefManager,
        itemDao: ItemDao
    ): LocalDataSource {
        return LocalDataSource(sharedPrefManager,itemDao)
    }

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): RoomDB {
        return Room
            .databaseBuilder(context, RoomDB::class.java, "LocalDb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideItemDao(roomDB: RoomDB): ItemDao {
        return roomDB.itemDao()
    }
}