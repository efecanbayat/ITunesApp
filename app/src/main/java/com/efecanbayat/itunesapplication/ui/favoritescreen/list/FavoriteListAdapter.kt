package com.efecanbayat.itunesapplication.ui.favoritescreen.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.efecanbayat.itunesapplication.R
import com.efecanbayat.itunesapplication.data.entity.DataList
import com.efecanbayat.itunesapplication.databinding.ItemDataBinding
import com.efecanbayat.itunesapplication.ui.searchscreen.IDataOnClickListener

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>() {

    private var favoriteList = ArrayList<DataList>()
    private var listener: IDataOnClickListener? = null

    inner class FavoriteListViewHolder(val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val binding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        val favoriteItem = favoriteList[position]

        holder.binding.apply {
            nameTextView.text = favoriteItem.collectionName ?: favoriteItem.trackName
            dateTextView.text = favoriteItem.releaseDate?.substring(0, 10)
            priceTextView.text =
                if (favoriteItem.collectionPrice != null) "\$${favoriteItem.collectionPrice}" else if (favoriteItem.price != null) "\$${favoriteItem.price}" else "Price not found"
            Glide.with(itemImageView.context)
                .load(favoriteItem.artworkUrl100)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(itemImageView)
        }

        holder.itemView.setOnClickListener {
            listener?.onClick(favoriteItem)
        }
    }

    override fun getItemCount(): Int = favoriteList.size

    fun addListener(listener: IDataOnClickListener) {
        this.listener = listener
    }

    fun removeListener() {
        this.listener = null
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFavoriteList(favoriteList: ArrayList<DataList>?) {
        favoriteList?.let {
            this.favoriteList = favoriteList
            notifyDataSetChanged()
        }
    }
}