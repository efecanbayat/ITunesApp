package com.efecanbayat.itunesapplication.data.remote

import com.efecanbayat.itunesapplication.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    suspend fun getDataByQuery(query: String, mediaType: String?) = getResult {
        apiService.getDataByQuery(query, mediaType)
    }
}