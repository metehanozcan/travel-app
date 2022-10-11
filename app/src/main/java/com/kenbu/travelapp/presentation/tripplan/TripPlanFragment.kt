package com.kenbu.travelapp.presentation.tripplan

import android.content.res.Resources
import android.graphics.drawable.Drawable
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
import com.google.android.material.tabs.TabLayoutMediator
import com.kenbu.travelapp.R
import com.kenbu.travelapp.databinding.FragmentSearchBinding
import com.kenbu.travelapp.databinding.FragmentTripPlanBinding
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.presentation.search.adapter.NearbyAdapter
import com.kenbu.travelapp.presentation.search.adapter.TopDestinationsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TripPlanFragment : Fragment() {
    private lateinit var binding: FragmentTripPlanBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTripPlanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TripPlanViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            if (position == 0) {
                tab.text = "Trips"
                tab.setIcon(R.drawable.filled_briefcase)
            } else if (position == 1) {
                tab.text = "Bookmark"
                tab.setIcon(R.drawable.filled_bookmark_)
            }
        }.attach()
    }
}







