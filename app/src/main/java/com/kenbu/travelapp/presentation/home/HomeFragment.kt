package com.kenbu.travelapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kenbu.travelapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.flightsButton.setOnClickListener {
            Log.v("Test", "Test124124")
        }
        val adapter = HomeCatalogViewPagerAdapter(this)
        binding.CatalogView.adapter = adapter
        TabLayoutMediator(
            binding.tabs, binding.CatalogView
        ) { tab, position ->
            if (position == 0) {
                tab.text = "All"
            } else if (position == 1) {
                tab.text = "Flights"
            } else if (position == 2) {
                tab.text = "Hotels"
            } else {
                tab.text = "Transportations"

            }
        }.attach()
    }
}