package com.example.barcode.ui.bars_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barcode.databinding.FragmentBarsListBinding

class BarsListFragment: Fragment() {

    private lateinit var binding: FragmentBarsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBarsListBinding.inflate(layoutInflater)
        return binding.root
    }
}