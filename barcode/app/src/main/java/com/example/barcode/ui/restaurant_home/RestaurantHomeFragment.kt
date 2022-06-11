package com.example.barcode.ui.restaurant_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barcode.databinding.FragmentRestaurantHomeBinding

class RestaurantHomeFragment: Fragment() {

    private lateinit var binding: FragmentRestaurantHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantHomeBinding.inflate(layoutInflater)
        return binding.root
    }
}