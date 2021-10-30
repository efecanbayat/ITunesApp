package com.efecanbayat.itunesapplication.ui.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.efecanbayat.itunesapplication.data.ApiRepository
import com.efecanbayat.itunesapplication.data.entity.BaseResponse
import com.efecanbayat.itunesapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getDetailById(id: String?): LiveData<Resource<BaseResponse>> {
        return apiRepository.getDetailById(id)
    }
}