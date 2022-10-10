package com.kenbu.travelapp.presentation.guide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.databinding.GuideScreenArticlesItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.utils.download


class ArticlesAdapter() : RecyclerView.Adapter<ArticlesAdapter.ArticleVH>() {
    class ArticleVH(val binding: GuideScreenArticlesItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleVH {
        val binding =
            GuideScreenArticlesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleVH(binding)
    }

    override fun onBindViewHolder(holder: ArticleVH, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {

            articleImgView.apply {
                download(item.images[0].url)
            }
            articleCategoryTitle.text = item.title
            articleTitle.text=item.description
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}