package com.kenbu.travelapp.presentation.tripplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kenbu.travelapp.R
import com.kenbu.travelapp.databinding.FragmentTripPlanBinding
import dagger.hilt.android.AndroidEntryPoint

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







