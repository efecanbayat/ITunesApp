package com.efecanbayat.itunesapplication.ui.searchscreen

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.itunesapplication.R
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.databinding.ItemDataBinding
import com.efecanbayat.itunesapplication.utils.ImageSizeHelper

class DataListAdapter : RecyclerView.Adapter<DataListAdapter.DataListViewHolder>() {

    private var dataList = ArrayList<DataList>()
    private var listener: IDataOnClickListener? = null

    inner class DataListViewHolder(val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val data = dataList[position]
        val resizedImage = ImageSizeHelper.imageSizeChanger300(data.artworkUrl100!!)

        holder.binding.apply {
            nameTextView.text = data.trackName ?: data.collectionName
            dateTextView.text = data.releaseDate?.substring(0,10)

            priceTextView.text = if(data.collectionPrice != null) "\$${data.collectionPrice}" else if(data.price != null) "\$${data.price}" else "Price not found"
            Glide.with(itemImageView.context)
                .load(resizedImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(itemImageView)
        }

        holder.itemView.setOnClickListener {
            listener?.onClick(data)
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun addListener(listener: IDataOnClickListener) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(dataList: ArrayList<DataList>?) {
        dataList?.let {
            this.dataList = dataList
            notifyDataSetChanged()
        }
    }
}