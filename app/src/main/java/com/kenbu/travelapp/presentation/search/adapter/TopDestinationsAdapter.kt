package com.kenbu.travelapp.presentation.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.SearchScreenNearbyAttractionsItemBinding
import com.kenbu.travelapp.databinding.SearchScreenTopDestinationsItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.search.SearchFragmentDirections
import com.kenbu.travelapp.utils.download


class TopDestinationsAdapter() : RecyclerView.Adapter<TopDestinationsAdapter.TopVH>() {
    class TopVH(val binding: SearchScreenTopDestinationsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
            return (oldItem == newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopVH {
        val binding =
            SearchScreenTopDestinationsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TopVH(binding)
    }

    override fun onBindViewHolder(holder: TopVH, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            searchScreenDestinationsItemImg.apply {
                download(item.images[0].url)
                Log.d("item", item.images[0].url)
            }
            topDestinationsCityTextLayout.text = item.city
            topDestinationsCountryText.text = item.country
        }
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                        item
                    )
                )
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}