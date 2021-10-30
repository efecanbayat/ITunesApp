package com.efecanbayat.itunesapplication.data

import com.efecanbayat.itunesapplication.data.remote.RemoteDataSource
import com.efecanbayat.itunesapplication.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {

    fun getDataByQuery(query: String, mediaType: String?, limit: Int) = performNetworkOperation {
        remoteDataSource.getDataByQuery(query, mediaType, limit)
    }

    fun getDetailById(id: String?) = performNetworkOperation {
        remoteDataSource.getDetailById(id)
    }
}