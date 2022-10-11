package com.kenbu.travelapp.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.FragmentDetailItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItemImage
import com.kenbu.travelapp.utils.download


class DetailAdapter(private val imageCallBack: (TravelAppModelItemImage) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.DetailVH>() {
    class DetailVH(val binding: FragmentDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    private var differCallBack = object : DiffUtil.ItemCallback<TravelAppModelItemImage>() {
        override fun areItemsTheSame(
            oldItem: TravelAppModelItemImage,
            newItem: TravelAppModelItemImage
        ): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(
            oldItem: TravelAppModelItemImage,
            newItem: TravelAppModelItemImage
        ): Boolean {
            return (oldItem == newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVH {
        val binding =
            FragmentDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return DetailVH(binding)

    }

    override fun onBindViewHolder(holder: DetailVH, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            itemImg.apply {
                download(item.url)
            }
            holder.itemView.setOnClickListener {
                imageCallBack(item)
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}