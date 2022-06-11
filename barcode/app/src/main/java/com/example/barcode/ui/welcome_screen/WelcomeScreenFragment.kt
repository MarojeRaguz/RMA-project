package com.example.barcode.ui.welcome_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.barcode.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment: Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeScreenBinding.inflate(layoutInflater)
        binding.tvContinueAsBar.setOnClickListener{continueAsBar()}
        binding.tvContinueAsGuest.setOnClickListener{continueAsGuest()}
        return binding.root
    }

    private fun continueAsBar(){
        val action = WelcomeScreenFragmentDirections.actionWelcomeScreenFragmentToLoginFragment()
        findNavController().navigate(action)
    }
    private fun continueAsGuest(){

        val action = WelcomeScreenFragmentDirections.actionWelcomeScreenFragmentToBarsListFragment()
        findNavController().navigate(action)

    }

}