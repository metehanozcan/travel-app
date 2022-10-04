package com.kenbu.travelapp.presentation.home
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kenbu.travelapp.presentation.dummy.Dummy

class HomeCatalogViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Dummy()
            1 -> Dummy()
            2 -> Dummy()
            3->  Dummy()
            else -> {
                Dummy()
            }
        }
    }


}