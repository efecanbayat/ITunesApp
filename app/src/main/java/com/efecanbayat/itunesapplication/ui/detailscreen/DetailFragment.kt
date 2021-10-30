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
import com.efecanbayat.itunesapplication.base.BaseFragment
import com.efecanbayat.itunesapplication.databinding.FragmentDetailBinding
import com.efecanbayat.itunesapplication.utils.Resource
import com.efecanbayat.itunesapplication.utils.gone
import com.efecanbayat.itunesapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

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
                        val data = response.data!!.results

                        Glide.with(requireContext())
                            .load(data!![0].artworkUrl100)
                            .into(detailImageView)

                        detailDateTextView.text = data[0].releaseDate?.substring(0, 10)
                        detailNameTextView.text = data[0].collectionName ?: data[0].trackName
                        detailDescriptionTextView.text =
                            if (data[0].longDescription != null) HtmlCompat.fromHtml(data[0].longDescription!!,HtmlCompat.FROM_HTML_MODE_LEGACY) else if (data[0].description != null) HtmlCompat.fromHtml(data[0].description!!,HtmlCompat.FROM_HTML_MODE_LEGACY) else "Description not found"
                        priceTextView.text =
                            if (data[0].collectionPrice != null) "\$${data[0].collectionPrice}" else if (data[0].price != null) "\$${data[0].price}" else "Price not found"
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
    }
}