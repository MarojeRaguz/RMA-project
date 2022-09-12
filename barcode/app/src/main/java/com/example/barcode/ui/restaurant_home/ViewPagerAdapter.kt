package com.example.barcode.ui.restaurant_home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.firebase.auth.FirebaseUser


class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return RestaurantHomeFragment()
            }
            1 -> {
                return NewPasswordFragment()
            }
            else -> {
                return RestaurantHomeFragment()
            }
        }
    }


}