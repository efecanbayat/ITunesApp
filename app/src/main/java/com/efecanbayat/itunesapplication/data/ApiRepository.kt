package com.efecanbayat.itunesapplication.data

import com.efecanbayat.itunesapplication.data.remote.RemoteDataSource
import com.efecanbayat.itunesapplication.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {

    fun getDataByQuery(query: String, mediaType: String?) = performNetworkOperation {
        remoteDataSource.getDataByQuery(query, mediaType)
    }
}