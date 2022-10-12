package com.kenbu.travelapp.presentation.guide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.R
import com.kenbu.travelapp.databinding.GuideButtonCategoryBinding
import com.kenbu.travelapp.domain.model.GuideModel


class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.CategoryVH>() {
    class CategoryVH(val binding: GuideButtonCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val category_list = mutableListOf(
        0,
        R.drawable.filled_taxi,
        R.drawable.filled_car,
        R.drawable.ic_unfilled_home,
        R.drawable.restaurant,
        R.drawable.resort,
        R.drawable.filled_hotel,
        R.drawable.sightseeing
    )

    private var differCallBack = object : DiffUtil.ItemCallback<GuideModel>() {
        override fun areItemsTheSame(
            oldItem: GuideModel,
            newItem: GuideModel
        ): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(
            oldItem: GuideModel,
            newItem: GuideModel
        ): Boolean {
            return (oldItem == newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding =
            GuideButtonCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryVH(binding)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            categoriesButton.text = item.title
            categoriesButton.setCompoundDrawablesWithIntrinsicBounds(
                category_list[item.id.toInt()],
                0,
                0,
                0
            )
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}