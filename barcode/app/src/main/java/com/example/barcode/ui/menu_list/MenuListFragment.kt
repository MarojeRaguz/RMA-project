package com.example.barcode.ui.menu_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barcode.databinding.FragmentMenuListBinding

class MenuListFragment : Fragment() {

    private lateinit var binding: FragmentMenuListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuListBinding.inflate(layoutInflater)
        return binding.root
    }
}