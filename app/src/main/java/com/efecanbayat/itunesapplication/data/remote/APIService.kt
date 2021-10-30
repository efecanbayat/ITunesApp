package com.efecanbayat.itunesapplication.data.remote

import com.efecanbayat.itunesapplication.data.entity.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search?")
    suspend fun getDataByQuery(
        @Query("term") term: String,
        @Query("media") media: String?,
        @Query("limit") limit: Int
    ): Response<BaseResponse>

    @GET("lookup?")
    suspend fun getDetailById(
        @Query("id") id: String?
    ): Response<BaseResponse>
}