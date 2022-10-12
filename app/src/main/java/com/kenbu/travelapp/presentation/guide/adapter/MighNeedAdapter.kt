package com.kenbu.travelapp.presentation.guide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.GuideScreenMightNeedTheseItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.guide.GuideFragmentDirections
import com.kenbu.travelapp.utils.download


class MighNeedAdapter() : RecyclerView.Adapter<MighNeedAdapter.MigtNeedVH>() {
    class MigtNeedVH(val binding: GuideScreenMightNeedTheseItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MigtNeedVH {
        val binding =
            GuideScreenMightNeedTheseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MigtNeedVH(binding)
    }

    override fun onBindViewHolder(holder: MigtNeedVH, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            mightneedImg.apply {
                download(item.images[0].url.toString())
            }
            mightneedText.text = item.city
        }
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    GuideFragmentDirections.actionGuideFragmentToDetailFragment(
                        item
                    )
                )
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}