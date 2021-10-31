package com.efecanbayat.itunesapplication.ui.detailscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.efecanbayat.itunesapplication.R
import com.efecanbayat.itunesapplication.base.BaseFragment
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.databinding.FragmentDetailBinding
import com.efecanbayat.itunesapplication.utils.ImageSizeHelper
import com.efecanbayat.itunesapplication.utils.Resource
import com.efecanbayat.itunesapplication.utils.gone
import com.efecanbayat.itunesapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private var data: List<DataList>? = emptyList()
    private lateinit var detailItem: DataList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDetail()
        addListeners()
    }

    @SuppressLint("SetTextI18n")
    private fun fetchDetail() {

        viewModel.getDetailById(args.trackId).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    binding.apply {
                        itemConstraintLayout.gone()
                        loadingAnimation.show()
                    }
                }
                Resource.Status.SUCCESS -> {
                    binding.apply {
                        itemConstraintLayout.show()
                        loadingAnimation.gone()

                        data = response.data!!.results
                        for (item in data!!){
                            detailItem = item
                        }
                        val resizedImage = ImageSizeHelper.imageSizeChanger400(detailItem.artworkUrl100!!)
                        Glide.with(requireContext())
                            .load(resizedImage)
                            .into(detailImageView)

                        detailDateTextView.text = detailItem.releaseDate?.substring(0, 10)
                        detailNameTextView.text = detailItem.trackName ?: detailItem.collectionName
                        detailDescriptionTextView.text =
                            when {
                                detailItem.longDescription != null -> HtmlCompat.fromHtml(
                                    detailItem.longDescription!!,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                                detailItem.description != null -> HtmlCompat.fromHtml(
                                    detailItem.description!!,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                                else -> "Description not found"
                            }
                        priceTextView.text =
                            if (detailItem.collectionPrice != null) "\$${detailItem.collectionPrice}" else if (detailItem.price != null) "\$${detailItem.price}" else "Price not found"

                        if (isFavorite()) {
                            favoriteImageView.setImageResource(R.drawable.ic_favorite)
                        } else {
                            favoriteImageView.setImageResource(R.drawable.ic_not_favorite)
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    binding.apply {
                        itemConstraintLayout.gone()
                        loadingAnimation.gone()
                    }
                    Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun addListeners() {

        binding.backImageView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.favoriteImageView.setOnClickListener {
            if (!isFavorite()) {
                viewModel.addFavoriteItem(detailItem)
                binding.favoriteImageView.setImageResource(R.drawable.ic_favorite)
            } else {
                viewModel.deleteFavoriteItem(detailItem)
                binding.favoriteImageView.setImageResource(R.drawable.ic_not_favorite)
            }
        }
    }

    private fun isFavorite(): Boolean {
        var isFavorite = false

        for (roomItem in viewModel.getFavoriteItems()) {
            isFavorite = roomItem.trackId == detailItem.trackId

            if (isFavorite) {
                break
            }
        }
        return isFavorite
    }
}