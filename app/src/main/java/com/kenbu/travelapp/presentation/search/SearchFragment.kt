package com.kenbu.travelapp.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kenbu.travelapp.databinding.FragmentSearchBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.search.adapter.NearbyAdapter
import com.kenbu.travelapp.presentation.search.adapter.TopDestinationsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var nearByAdapter: NearbyAdapter
    private lateinit var topDestinationsAdapter: TopDestinationsAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSetup()
        observeData()

    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    it.isLoading.let {
                        if (!it) {
                            Log.d("test1111", "tessssssss")
                        } else {
                            Log.d("test2222", "tessssssss")
                        }
                    }
                    it.nearbyItem.let { list ->
                        nearByAdapter.differ.submitList(list)
                    }
                    it.topDestinationsItem.let { list ->
                        topDestinationsAdapter.differ.submitList(list)
                    }
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        nearByAdapter = NearbyAdapter(::setBookMark)
        topDestinationsAdapter = TopDestinationsAdapter()
        binding.apply {
            binding.topdestinationsRecyclerview.adapter = topDestinationsAdapter
            binding.nearbyRecyclerview.adapter = nearByAdapter
        }
    }

    private fun setBookMark(bookMark: TravelAppModelItem) {
        viewModel.updateData(bookMark.id,bookMark)
    }
}







