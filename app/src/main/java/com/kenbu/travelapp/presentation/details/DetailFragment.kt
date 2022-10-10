package com.kenbu.travelapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kenbu.travelapp.databinding.FragmentDetailBinding
import com.kenbu.travelapp.utils.download
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

    }

    private fun observeData() {
        val travelList = args.travelList
        detailAdapter = DetailAdapter()
        binding.detailRecyclerView.adapter = detailAdapter
        detailAdapter.differ.submitList(travelList.images)
        binding.detailTitle.text = travelList.title
        binding.detailCountryTitle.text = "${travelList.city},\n${travelList.country}"
        binding.detailDescription.text = travelList.description
        binding.articleImgView.download(travelList.images[0].url)
    }

}