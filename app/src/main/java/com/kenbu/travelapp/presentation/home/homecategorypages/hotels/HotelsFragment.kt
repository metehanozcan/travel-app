package com.kenbu.travelapp.presentation.home.homecategorypages.hotels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.databinding.FragmentCategoriesHotelBinding
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import com.kenbu.travelapp.presentation.home.homecategorypages.HotelsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotelsFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesHotelBinding
    private lateinit var hotelAdapter: HomeAdapter
    private val viewModel: HotelsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesHotelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewSetup()
        observeData()

    }

    private fun observeData() {
        viewModel.viewModelScope.launch {
            viewModel.uiState.collect {
                it.isLoading.let {

                }
                it.categoryHotelItems.let { list ->
                    Log.d("home", list.toString())
                    hotelAdapter.differ.submitList(list)
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        hotelAdapter = HomeAdapter()
        binding.apply {
            categoriesHotelRecyclerview.adapter = hotelAdapter
        }
    }

}







