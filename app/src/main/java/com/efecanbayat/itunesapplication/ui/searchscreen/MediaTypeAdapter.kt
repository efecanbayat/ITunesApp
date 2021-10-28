package com.efecanbayat.itunesapplication.ui.searchscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.itunesapplication.R
import com.efecanbayat.itunesapplication.data.entity.MediaType
import com.efecanbayat.itunesapplication.databinding.ItemMediaTypeBinding

class MediaTypeAdapter : RecyclerView.Adapter<MediaTypeAdapter.MediaTypeViewHolder>() {

    private var mediaTypeList = ArrayList<MediaType>()
    private var listener: IMediaTypeOnClickListener? = null
    private var selectedItem = 0

    inner class MediaTypeViewHolder(val binding: ItemMediaTypeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaTypeViewHolder {
        val binding =
            ItemMediaTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaTypeViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MediaTypeViewHolder, position: Int) {
        val mediaType = mediaTypeList[position]

        holder.binding.apply {

            Glide.with(mediaTypeImageView.context)
                .load(mediaType.mediaTypeImage).into(mediaTypeImageView)

            mediaTypeNameTextView.text = mediaType.mediaTypeName

            if (selectedItem == position) {
                mediaTypeCardView.setCardBackgroundColor(
                    mediaTypeCardView.context.getColor(
                        R.color.button_blue
                    )
                )
            } else {
                mediaTypeCardView.setCardBackgroundColor(
                    mediaTypeCardView.context.getColor(
                        R.color.white
                    )
                )
            }
        }

        holder.itemView.setOnClickListener {
            selectedItem = holder.adapterPosition
            listener?.onClick(mediaType)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = mediaTypeList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMediaTypeList(mediaTypeList: List<MediaType>) {
        this.mediaTypeList = ArrayList(mediaTypeList)
        notifyDataSetChanged()
    }

    fun addListener(listener: IMediaTypeOnClickListener) {
        this.listener = listener
    }
}