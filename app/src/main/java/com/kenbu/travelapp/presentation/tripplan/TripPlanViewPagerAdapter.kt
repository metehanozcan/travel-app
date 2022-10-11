package com.kenbu.travelapp.presentation.tripplan

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kenbu.travelapp.presentation.tripplan.bookmarkpage.BookMarkFragment

class TripPlanViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BookMarkFragment()
            else -> {BookMarkFragment()}
        }
    }
}