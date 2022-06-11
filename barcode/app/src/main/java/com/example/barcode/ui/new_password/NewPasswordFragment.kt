package com.example.barcode.ui.new_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.barcode.databinding.FragmentNewPasswordBinding

class NewPasswordFragment: Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPasswordBinding.inflate(layoutInflater)
        return binding.root
    }
}