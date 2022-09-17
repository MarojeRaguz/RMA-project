package com.example.barcode.ui.restaurant_home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.databinding.DialogConfirmOrderBinding
import com.example.barcode.databinding.DialogRestaurantViewOrderBinding
import com.example.barcode.model.Order

class ViewOrderDialog(var order: Order) : DialogFragment() {

    lateinit var binding: DialogRestaurantViewOrderBinding
    private lateinit var adapter: ViewOrderDialogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogRestaurantViewOrderBinding.inflate(layoutInflater)
        binding.tvTableNumber.text = "BROJ STOLA: ${order.table}"
        var total = 0.0;
        order.articles.forEach { article -> total += article.price * article.count}
        binding.tvTotal.text = "UKUPNO: $total kn"
        setupRecycleView()
        return binding.root
    }

     private fun setupRecycleView() {
        adapter = ViewOrderDialogAdapter(order)
        binding.rvViewOrder.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,false
        )
        binding.rvViewOrder.adapter = adapter
    }

}

