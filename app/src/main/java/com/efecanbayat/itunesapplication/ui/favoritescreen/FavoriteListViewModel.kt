package com.efecanbayat.itunesapplication.ui.favoritescreen

import androidx.lifecycle.ViewModel
import com.efecanbayat.itunesapplication.data.ApiRepository
import com.efecanbayat.itunesapplication.data.entity.DataList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    var favoriteList: ArrayList<DataList>? = arrayListOf()

    fun getFavoriteItems() {
        favoriteList = apiRepository.getFavoriteItems() as ArrayList<DataList>
    }
}