package com.kenbu.travelapp.presentation.search.searchengine

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.FragmentSearchEngineItemBinding
import com.kenbu.travelapp.databinding.FragmentTripPlanBookmarkItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.utils.download


class SearchEngineAdapter() :
    RecyclerView.Adapter<SearchEngineAdapter.SearchEngineVH>() {
    class SearchEngineVH(val binding: FragmentSearchEngineItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchEngineVH {
        val binding =
            FragmentSearchEngineItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SearchEngineVH(binding)
    }

    override fun onBindViewHolder(holder: SearchEngineVH, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            Log.d("item", item.toString())
            destImg.apply {
                download(item.images[0].url)
                Log.d("item", item.images[0].url)
            }
            searchAttractionsTypeTextLayout.text = item.category
            topDestinationsCityTextLayout.text = item.city
            topDestinationsCountryText.text = item.country

        }
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    SearchEngineFragmentDirections.actionSearchEngineFragmentToDetailFragment(
                        item
                    )
                )
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}