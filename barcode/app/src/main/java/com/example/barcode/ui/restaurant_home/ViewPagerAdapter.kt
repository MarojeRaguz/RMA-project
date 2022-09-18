package com.example.barcode.ui.restaurant_home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                RestaurantHomeFragment()
            }
            1 -> {
                NewPasswordFragment()
            }
            else -> {
                RestaurantHomeFragment()
            }
        }
    }


}