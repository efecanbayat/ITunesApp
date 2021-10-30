package com.efecanbayat.itunesapplication.ui.searchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.efecanbayat.itunesapplication.data.ApiRepository
import com.efecanbayat.itunesapplication.data.entity.BaseResponse
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    var dataList: ArrayList<DataList>? = arrayListOf()
    var mediaType: String? = "movie"

    fun getDataByQuery(query: String, limit: Int): LiveData<Resource<BaseResponse>> {
        return apiRepository.getDataByQuery(query, mediaType, limit)
    }
}