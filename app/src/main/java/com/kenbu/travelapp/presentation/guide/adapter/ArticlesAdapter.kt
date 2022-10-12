package com.kenbu.travelapp.presentation.guide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kenbu.travelapp.R
import com.kenbu.travelapp.databinding.GuideScreenArticlesItemBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.guide.GuideFragmentDirections
import com.kenbu.travelapp.utils.download


class ArticlesAdapter(private val bookMarkCallBack :(TravelAppModelItem)->Unit) : RecyclerView.Adapter<ArticlesAdapter.ArticleVH>() {
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
            if (item.isBookmark) {
                guideBookmarkButton.setIconResource(R.drawable.filled_bookmark_)
                guideBookmarkButton.setIconTintResource(R.color.pink)
            } else {
                guideBookmarkButton.setIconResource(R.drawable.unfilled_bookmark)
                guideBookmarkButton.setIconTintResource(R.color.white)
            }
            guideBookmarkButton.setOnClickListener {

                if (!item.isBookmark) {
                    guideBookmarkButton.setIconResource(R.drawable.filled_bookmark_)
                    guideBookmarkButton.setIconTintResource(R.color.pink)
                    item.isBookmark = true

                } else {
                    guideBookmarkButton.setIconResource(R.drawable.unfilled_bookmark)
                    guideBookmarkButton.setIconTintResource(R.color.white)
                    item.isBookmark = false
                }
                bookMarkCallBack(item)
            }
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