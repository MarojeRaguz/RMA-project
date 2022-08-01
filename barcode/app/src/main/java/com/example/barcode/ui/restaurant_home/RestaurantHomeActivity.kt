package com.example.barcode.ui.restaurant_home

import android.app.Activity
import android.os.Bundle
import com.example.barcode.databinding.ActivityBarBinding
import com.example.barcode.databinding.ActivityMainBinding

class RestaurantHomeActivity: Activity() {

    lateinit var binding: ActivityBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}