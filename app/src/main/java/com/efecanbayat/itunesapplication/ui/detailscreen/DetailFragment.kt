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
import com.efecanbayat.itunesapplication.data.entity.RoomDataList
import com.efecanbayat.itunesapplication.databinding.FragmentDetailBinding
import com.efecanbayat.itunesapplication.utils.Resource
import com.efecanbayat.itunesapplication.utils.gone
import com.efecanbayat.itunesapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private var data: List<DataList>? = emptyList()
    private lateinit var item: RoomDataList

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

                        if (isFavorite()) {
                            favoriteImageView.setImageResource(R.drawable.ic_favorite)
                        } else {
                            favoriteImageView.setImageResource(R.drawable.ic_not_favorite)
                        }

                        Glide.with(requireContext())
                            .load(data!![0].artworkUrl100)
                            .into(detailImageView)

                        detailDateTextView.text = data!![0].releaseDate?.substring(0, 10)
                        detailNameTextView.text = data!![0].collectionName ?: data!![0].trackName
                        detailDescriptionTextView.text =
                            when {
                                data!![0].longDescription != null -> HtmlCompat.fromHtml(
                                    data!![0].longDescription!!,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                                data!![0].description != null -> HtmlCompat.fromHtml(
                                    data!![0].description!!,
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                                else -> "Description not found"
                            }
                        priceTextView.text =
                            if (data!![0].collectionPrice != null) "\$${data!![0].collectionPrice}" else if (data!![0].price != null) "\$${data!![0].price}" else "Price not found"

                        item = RoomDataList(
                            data!![0].trackId,
                            data!![0].artworkUrl100,
                            data!![0].trackName,
                            data!![0].longDescription,
                            data!![0].shortDescription,
                            data!![0].description,
                            data!![0].kind,
                            data!![0].releaseDate,
                            data!![0].collectionPrice,
                            data!![0].price,
                            data!![0].collectionName
                        )
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
                viewModel.addFavoriteItem(item)
                binding.favoriteImageView.setImageResource(R.drawable.ic_favorite)
            } else {
                viewModel.deleteFavoriteItem(item)
                binding.favoriteImageView.setImageResource(R.drawable.ic_not_favorite)
            }
        }
    }

    private fun isFavorite(): Boolean {
        var isFavorite = false

        for (item in viewModel.getFavoriteItems()) {
            isFavorite = item.trackId == data!![0].trackId

            if (isFavorite) {
                break
            }
        }
        return isFavorite
    }
}