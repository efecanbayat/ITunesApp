package com.efecanbayat.itunesapplication.data

import com.efecanbayat.itunesapplication.data.remote.RemoteDataSource
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource
) {

}