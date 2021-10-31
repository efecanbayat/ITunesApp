package com.efecanbayat.itunesapplication.ui.favoritescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.efecanbayat.itunesapplication.base.BaseFragment
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.databinding.FragmentFavoriteListBinding
import com.efecanbayat.itunesapplication.ui.searchscreen.IDataOnClickListener
import com.efecanbayat.itunesapplication.utils.hide
import com.efecanbayat.itunesapplication.utils.show
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
        addListeners()
    }

    private fun initAdapters() {
        binding.favoriteRecyclerView.adapter = favoriteListAdapter
    }

    private fun getFavoriteItems() {
        viewModel.getFavoriteItems()
        if (viewModel.favoriteList.isNullOrEmpty()){
            binding.favoriteRecyclerView.hide()
            binding.messageTextView.show()

        }else{
            binding.favoriteRecyclerView.show()
            binding.messageTextView.hide()
            favoriteListAdapter.setFavoriteList(viewModel.favoriteList)
        }
    }

    private fun addListeners() {
        favoriteListAdapter.addListener(object : IDataOnClickListener {
            override fun onClick(dataList: DataList) {
                val action = FavoriteListFragmentDirections.actionFavoriteListFragmentToDetailFragment(dataList.trackId)
                findNavController().navigate(action)
                favoriteListAdapter.removeListener()
            }
        })
    }
}