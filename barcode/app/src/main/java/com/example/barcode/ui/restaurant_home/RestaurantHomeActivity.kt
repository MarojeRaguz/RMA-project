package com.example.barcode.ui.restaurant_home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.barcode.databinding.ActivityRestaurantHomeBinding

class RestaurantHomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityRestaurantHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestaurantHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        val indicator = binding.circleIndicator
        indicator.setViewPager(viewPager)
    }
}