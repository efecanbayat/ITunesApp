package com.efecanbayat.itunesapplication.data.remote

import com.efecanbayat.itunesapplication.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    suspend fun getDataByQuery(query: String, mediaType: String?, limit: Int) = getResult {
        apiService.getDataByQuery(query, mediaType, limit)
    }

    suspend fun getDetailById(id: String?) = getResult {
        apiService.getDetailById(id)
    }
}