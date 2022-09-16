package com.example.barcode.ui.restaurant_home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.barcode.data.BarRepository
import com.example.barcode.data.BarRepositoryImpl
import com.example.barcode.databinding.ActivityRestaurantHomeBinding
import com.google.firebase.auth.FirebaseAuth

class RestaurantHomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityRestaurantHomeBinding
    private var barRepository: BarRepository = BarRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestaurantHomeBinding.inflate(layoutInflater)
        binding.tvBarName.text = barRepository.getBarByBarEmail(FirebaseAuth.getInstance().currentUser!!.email.toString()).name
        setContentView(binding.root)

        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        val indicator = binding.circleIndicator
        indicator.setViewPager(viewPager)
    }
}