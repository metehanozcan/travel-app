package com.kenbu.travelapp.presentation.home.homecategorypages.transportation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.databinding.FragmentCategoriesTransportationBinding
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import com.kenbu.travelapp.presentation.home.homecategorypages.TransportationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransportationsFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesTransportationBinding
    private lateinit var transportationAdapter: HomeAdapter
    private val viewModel: TransportationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesTransportationBinding.inflate(layoutInflater)
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
                it.categoryTransportationItems.let { list ->
                    Log.d("home", list.toString())
                    transportationAdapter.differ.submitList(list)
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        transportationAdapter = HomeAdapter()
        binding.apply {
            categoriesTransportationRecyclerview.adapter = transportationAdapter
        }
    }

}







