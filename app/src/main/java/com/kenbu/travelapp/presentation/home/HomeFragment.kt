package com.kenbu.travelapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kenbu.travelapp.databinding.FragmentHomeBinding
import com.kenbu.travelapp.presentation.home.adapter.HomeAdapter
import com.kenbu.travelapp.presentation.home.adapter.HomeViewPagerAdapter
//import com.kenbu.travelapp.presentation.home.adapter.HomeViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
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
        setupViewPager()
        bindingInit()
    }
    /*
    Buton binding bağlantıları
    Button binding init
     */
    private fun bindingInit() {
        viewModel.viewModelScope.launch {
                binding.apply {
                    flightsButton.setOnClickListener {
                        tabs.getTabAt(1)?.select()
//                        adapter.differ.submitList(state.categoryFlightItems)
                    }
                    hotelsButton.setOnClickListener {
                        tabs.getTabAt(2)?.select()
//                        adapter.differ.submitList(state.categoryHotelItems)
                    }
                    carsButton.setOnClickListener {
                        tabs.getTabAt(3)?.select()
//                        adapter.differ.submitList(state.categoryTransportationItems)
                    }
                    taxiButton.setOnClickListener {
                        tabs.getTabAt(3)?.select()
//                        adapter.differ.submitList(state.categoryTransportationItems)
                    }
            }
        }
    }
    /*
     View Pager'ı hazırladığımız kısım
     */
    private fun setupViewPager(){
        val viewadapter = HomeViewPagerAdapter(this)
        binding.CatalogView.adapter = viewadapter
        TabLayoutMediator(
            binding.tabs, binding.CatalogView
        ) { tab, position ->
            if (position == 0) {
                tab.text = "All"
            } else if (position == 1) {
                tab.text = "Flights"
            } else if(position == 2) {
                tab.text = "Hotels"
            } else {
                tab.text = "Transportations"
            }
        }.attach()
    }
}






