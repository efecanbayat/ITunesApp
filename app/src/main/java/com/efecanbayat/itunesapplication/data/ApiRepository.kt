package com.efecanbayat.itunesapplication.data

import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.data.local.LocalDataSource
import com.efecanbayat.itunesapplication.data.remote.RemoteDataSource
import com.efecanbayat.itunesapplication.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun getDataByQuery(query: String, mediaType: String?, limit: Int) = performNetworkOperation {
        remoteDataSource.getDataByQuery(query, mediaType, limit)
    }

    fun getDetailById(id: String?) = performNetworkOperation {
        remoteDataSource.getDetailById(id)
    }

    fun getFavoriteItems() = localDataSource.getFavoriteItems()

    fun addFavoriteItem(item: DataList) = localDataSource.addFavoriteItem(item)

    fun deleteFavoriteItem(item: DataList) = localDataSource.deleteFavoriteItem(item)
}