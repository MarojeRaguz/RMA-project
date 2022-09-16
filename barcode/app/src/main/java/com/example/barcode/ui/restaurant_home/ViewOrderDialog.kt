package com.example.barcode.ui.restaurant_home

import android.app.Dialog
import android.os.Bundle

import androidx.fragment.app.DialogFragment
import com.example.barcode.databinding.DialogRestaurantViewOrderBinding
import com.example.barcode.model.Order

class ViewOrderDialog(var order: Order) : DialogFragment() {

    lateinit var binding: DialogRestaurantViewOrderBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        binding = DialogRestaurantViewOrderBinding.inflate(layoutInflater)
    }
}