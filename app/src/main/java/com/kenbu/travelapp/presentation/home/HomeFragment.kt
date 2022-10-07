package com.kenbu.travelapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.kenbu.travelapp.databinding.FragmentHomeBinding
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
//        tabBindingInit()
        observeData()
        bindingInit()

    }

    private fun tabBindingInit() {
        viewModel.viewModelScope.launch {
            viewModel.uiState.collect { state ->
                binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (binding.tabs.selectedTabPosition == 0){
                                adapter.differ.submitList(state.homeItems)
                           }
                        else if (binding.tabs.selectedTabPosition == 1) {
                            adapter.differ.submitList(state.categoryFlightItems)
                            adapter.notifyDataSetChanged()}
                        else if (binding.tabs.selectedTabPosition == 2)
                            adapter.differ.submitList(state.categoryHotelItems)
                        else if (binding.tabs.selectedTabPosition == 3)
                            adapter.differ.submitList(state.categoryTransportationItems)
                        else adapter.differ.submitList(state.homeItems)
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        // Handle tab unselect
                    }
                })

            }
        }
    }


    private fun bindingInit() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                binding.apply {
                    flightsButton.setOnClickListener {
                        tabs.getTabAt(1)?.select()
                        adapter.differ.submitList(state.categoryFlightItems)
                    }
                    hotelsButton.setOnClickListener {
                        tabs.getTabAt(2)?.select()
                        adapter.differ.submitList(state.categoryHotelItems)
                    }
                    carsButton.setOnClickListener {
                        tabs.getTabAt(3)?.select()
                        adapter.differ.submitList(state.categoryTransportationItems)
                    }
                    taxiButton.setOnClickListener {
                        tabs.getTabAt(3)?.select()
                        adapter.differ.submitList(state.categoryTransportationItems)
                    }
                }
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                it.isLoading.let {
                    if (!it) {
                        Log.d("test1111", "tessssssss")
                    } else {
                        Log.d("test2222", "tessssssss")
                    }
                }
                it.homeItems.let { list ->
                    adapter.differ.submitList(list)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeAdapter()
        binding.apply {
            binding.recyclerView.adapter = adapter
        }
    }
}




