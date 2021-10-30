package com.efecanbayat.itunesapplication.ui.searchscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.efecanbayat.itunesapplication.R
import com.efecanbayat.itunesapplication.base.BaseFragment
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.data.entity.MediaType
import com.efecanbayat.itunesapplication.databinding.FragmentSearchBinding
import com.efecanbayat.itunesapplication.utils.Resource
import com.efecanbayat.itunesapplication.utils.gone
import com.efecanbayat.itunesapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private val mediaTypeAdapter = MediaTypeAdapter()
    private val dataListAdapter = DataListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        initMediaTypes()
        addListeners()
    }

    private fun initAdapters() {
        binding.mediaTypeRecyclerView.adapter = mediaTypeAdapter
    }

    private fun initMediaTypes() {
        val mediaTypeList = ArrayList<MediaType>()
        mediaTypeList.add(MediaType(R.drawable.ic_movie, "Movie"))
        mediaTypeList.add(MediaType(R.drawable.ic_music, "Music"))
        mediaTypeList.add(MediaType(R.drawable.ic_book, "Ebook"))
        mediaTypeList.add(MediaType(R.drawable.ic_podcast, "Podcast"))

        mediaTypeAdapter.setMediaTypeList(mediaTypeList)
    }

    private fun addListeners() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (binding.searchView.query.length <= 2) {
                    binding.notFoundTextView.gone()
                    dataListAdapter.setDataList(arrayListOf())
                } else {
                    fetchSearchData(newText!!)
                }
                return true
            }
        })

        mediaTypeAdapter.addListener(object : IMediaTypeOnClickListener {
            override fun onClick(mediaType: MediaType) {
                viewModel.mediaType = mediaType.mediaTypeName.lowercase()
                fetchSearchData(binding.searchView.query.toString())
            }
        })

        dataListAdapter.addListener(object : IDataOnClickListener {
            override fun onClick(dataList: DataList) {
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(dataList.trackId)
                findNavController().navigate(action)
                dataListAdapter.removeListener()
            }

        })
    }

    private fun fetchSearchData(query: String) {

        if (query.length > 2) {
            viewModel.getDataByQuery(query).observe(viewLifecycleOwner, { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        binding.loadingAnimation.show()
                        binding.dataRecyclerView.gone()
                        binding.notFoundTextView.gone()
                    }
                    Resource.Status.SUCCESS -> {
                        binding.loadingAnimation.gone()
                        binding.dataRecyclerView.show()
                        binding.notFoundTextView.gone()

                        if (response.data?.resultCount != 0) {
                            viewModel.dataList = response.data?.results
                            setData(viewModel.dataList)
                            binding.dataRecyclerView.adapter = dataListAdapter
                        } else {
                            binding.dataRecyclerView.gone()
                            binding.notFoundTextView.show()
                        }
                    }
                    Resource.Status.ERROR -> {
                        binding.loadingAnimation.gone()
                        binding.dataRecyclerView.gone()
                        Toast.makeText(requireContext(), "Error! Try Again", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
        }
    }

    private fun setData(dataList: ArrayList<DataList>?) {
        dataListAdapter.setDataList(dataList)
    }
}