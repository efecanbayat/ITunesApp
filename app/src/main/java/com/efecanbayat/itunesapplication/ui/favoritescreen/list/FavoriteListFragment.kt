package com.efecanbayat.itunesapplication.ui.favoritescreen.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.efecanbayat.itunesapplication.base.BaseFragment
import com.efecanbayat.itunesapplication.databinding.FragmentFavoriteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteListFragment :
    BaseFragment<FragmentFavoriteListBinding>(FragmentFavoriteListBinding::inflate) {

    private val viewModel: FavoriteListViewModel by viewModels()
    private val favoriteListAdapter by lazy { FavoriteListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        getFavoriteItems()
    }

    private fun initAdapters() {
        binding.favoriteRecyclerView.adapter = favoriteListAdapter
    }

    private fun getFavoriteItems() {
        viewModel.getFavoriteItems()
        favoriteListAdapter.setFavoriteList(viewModel.favoriteList)
    }

}