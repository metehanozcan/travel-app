package com.kenbu.travelapp.presentation.tripplan.trippage

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.FragmentTripPageItemBinding
import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.utils.download


class TripPageAdapter() :
    RecyclerView.Adapter<TripPageAdapter.TripPageVH>() {
    class TripPageVH(val binding: FragmentTripPageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var differCallBack = object : DiffUtil.ItemCallback<TripPlanModel>() {
        override fun areItemsTheSame(
            oldItem: TripPlanModel,
            newItem: TripPlanModel
        ): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(
            oldItem: TripPlanModel,
            newItem: TripPlanModel
        ): Boolean {
            return (oldItem == newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripPageVH {
        val binding =
            FragmentTripPageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TripPageVH(binding)
    }

    override fun onBindViewHolder(holder: TripPageVH, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            Log.d("item", item.toString())
            destImg.apply {
                download("https://storage.googleapis.com/buro-malaysia-storage/beta.toffeetest.com/buro/2021/12/adfa4326-images_ca-interstate-travel-malaysia-2021.jpg")
            }
            topDestinationsCityTextLayout.text = item.destination
            topDestinationsCountryText.text = "${item.departureDate} - ${item.returnDate}"
            searchAttractionsTypeTextLayout.text = "${item.dayToStay} days"
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}