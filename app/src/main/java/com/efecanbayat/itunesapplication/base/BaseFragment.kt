package com.efecanbayat.itunesapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.efecanbayat.itunesapplication.ui.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment(), IBottomNavigationBarInterface {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (isBottomNavigationBarVisible())
            (activity as MainActivity).showNavigationBar()
        else
            (activity as MainActivity).hideNavigationBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}