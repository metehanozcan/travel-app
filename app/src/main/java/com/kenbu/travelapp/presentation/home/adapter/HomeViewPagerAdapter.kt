package com.kenbu.travelapp.presentation.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kenbu.travelapp.presentation.home.homecategorypages.all.AllFragment
import com.kenbu.travelapp.presentation.home.homecategorypages.flights.FlightsFragment
import com.kenbu.travelapp.presentation.home.homecategorypages.hotels.HotelsFragment
import com.kenbu.travelapp.presentation.home.homecategorypages.transportation.TransportationsFragment


class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFragment()
            1 -> FlightsFragment()
            2 -> HotelsFragment()
            3 -> TransportationsFragment()
            else -> {
                AllFragment()
            }
        }
    }
}


