package com.kenbu.travelapp.presentation.home.homecategorypages.flights

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.databinding.FragmentCategoriesAllBinding
import com.kenbu.travelapp.databinding.FragmentCategoriesFlightBinding
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import com.kenbu.travelapp.presentation.home.homecategorypages.AllViewModel
import com.kenbu.travelapp.presentation.home.homecategorypages.FlightsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FlightsFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesFlightBinding
    private lateinit var flightAdapter: HomeAdapter
    private val viewModel: FlightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesFlightBinding.inflate(layoutInflater)
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
                it.categoryFlightItems.let { list ->
                    Log.d("home", list.toString())
                    flightAdapter.differ.submitList(list)
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        flightAdapter = HomeAdapter()
        binding.apply {
            categoriesFlightRecyclerview.adapter = flightAdapter
        }
    }

}







