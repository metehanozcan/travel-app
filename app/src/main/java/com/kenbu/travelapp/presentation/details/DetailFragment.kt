package com.kenbu.travelapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.kenbu.travelapp.databinding.FragmentDetailBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItemImage
import com.kenbu.travelapp.utils.download
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailAdapter
    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInit()
    }

    private fun bindingInit() {
        val travelList = args.travelList
        detailAdapter = DetailAdapter(::imageCallBack)
        binding.detailRecyclerView.adapter = detailAdapter
        detailAdapter.differ.submitList(travelList.images)
        binding.detailTitle.text = travelList.title
        binding.detailCountryTitle.text = "${travelList.city},\n${travelList.country}"
        binding.detailDescription.text = travelList.description
        binding.articleImgView.download(travelList.images[0].url)
        binding.button.setOnClickListener {
            travelList.isBookmark = true
            viewModel.viewModelScope.launch {
                viewModel.updateData(travelList.id,travelList)
                delay(500L)
            }
            Snackbar.make(
                it,
                "Bookmark Added",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun imageCallBack(newImage: TravelAppModelItemImage) {
        binding.articleImgView.download(newImage.url)
    }
}