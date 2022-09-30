package com.kenbu.travelapp.presentation.home
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kenbu.travelapp.SearchFragment

class HomeCatalogViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            1 -> SearchFragment()
            2 -> SearchFragment()
            3->  SearchFragment()
            else -> {
                SearchFragment()
            }
        }
    }


}