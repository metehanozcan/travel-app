package com.kenbu.travelapp.presentation.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.R
import com.kenbu.travelapp.databinding.SearchScreenNearbyAttractionsItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.search.SearchFragmentDirections
import com.kenbu.travelapp.utils.download


class NearbyAdapter(private val bookMarkCallBack: (TravelAppModelItem) -> Unit) :
    RecyclerView.Adapter<NearbyAdapter.NearbyVH>() {
    class NearbyVH(val binding: SearchScreenNearbyAttractionsItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyVH {
        val binding =
            SearchScreenNearbyAttractionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NearbyVH(binding)
    }

    override fun onBindViewHolder(holder: NearbyVH, position: Int) {
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
            //BOOKMARK BUTTON BINDING IMPLEMENTATION
            if (item.isBookmark) {
                searchBookmarkButton.setIconResource(R.drawable.filled_bookmark_)
                searchBookmarkButton.setIconTintResource(R.color.pink)
            } else {
                searchBookmarkButton.setIconResource(R.drawable.unfilled_bookmark)
                searchBookmarkButton.setIconTintResource(R.color.white)
            }
            searchBookmarkButton.setOnClickListener {
                if (!item.isBookmark) {
                    searchBookmarkButton.setIconResource(R.drawable.filled_bookmark_)
                    searchBookmarkButton.setIconTintResource(R.color.pink)
                    item.isBookmark = true

                } else {
                    searchBookmarkButton.setIconResource(R.drawable.unfilled_bookmark)
                    searchBookmarkButton.setIconTintResource(R.color.white)
                    item.isBookmark = false
                }
                bookMarkCallBack(item)
            }
            //NAV TO DETAIL
            holder.itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                            item
                        )
                    )
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}