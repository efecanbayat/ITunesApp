package com.efecanbayat.itunesapplication.ui.favoritescreen.list

import android.os.Bundle
import android.view.View
import com.efecanbayat.itunesapplication.base.BaseFragment
import com.efecanbayat.itunesapplication.databinding.FragmentFavoriteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteListFragment: BaseFragment<FragmentFavoriteListBinding>(FragmentFavoriteListBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}