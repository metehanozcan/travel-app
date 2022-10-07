package com.kenbu.travelapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.HomeScreenListItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.model.TravelAppModelItemImage
import com.kenbu.travelapp.utils.download


class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeVH>() {
    class HomeVH(val binding: HomeScreenListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var differCallBack = object : DiffUtil.ItemCallback<TravelAppModelItem>() {
        override fun areItemsTheSame(
            oldItem: TravelAppModelItem,
            newItem: TravelAppModelItem
        ): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(
            oldItem: TravelAppModelItem,
            newItem: TravelAppModelItem
        ): Boolean {
            return (oldItem.images[0]==newItem.images[0])
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val binding =
            HomeScreenListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeVH(binding)
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
       val item = differ.currentList[position]
        holder.binding.apply {

            itemImg.apply{
                download(item.images[0].url)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}