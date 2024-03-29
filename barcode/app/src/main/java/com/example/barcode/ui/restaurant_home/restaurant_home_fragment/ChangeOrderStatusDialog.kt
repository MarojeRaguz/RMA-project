package com.example.barcode.ui.restaurant_home.restaurant_home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.barcode.R
import com.example.barcode.data.OrderRepository
import com.example.barcode.data.OrderRepositoryImpl
import com.example.barcode.databinding.DialogChangeOrderStatusBinding
import com.example.barcode.model.Order
import com.example.barcode.model.OrderStatus
import com.example.barcode.utils.getColorResource

class ChangeOrderStatusDialog(var order: Order): DialogFragment() {

    lateinit var binding: DialogChangeOrderStatusBinding
    private var orderRepository: OrderRepository = OrderRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogChangeOrderStatusBinding.inflate(layoutInflater)
        if (order.status == OrderStatus.ORDERED){
            binding.btnChangeStatus.text = "Označi narudžbu kao dostavljenu"
            binding.btnChangeStatus.setBackgroundColor(resources.getColor(R.color.delivered))
            binding.btnChangeStatus.setOnClickListener { changeStatus(OrderStatus.DELIVERED) }
        }else{
            binding.btnChangeStatus.text = "Obriši narudžbu"
            binding.btnChangeStatus.setOnClickListener { changeStatus(OrderStatus.DELETED) }

        }
        return binding.root
    }

    private fun changeStatus(orderStatus: OrderStatus) {
        order.status = orderStatus
        orderRepository.changeOrderStatus(order.id,order)
        Toast.makeText(context,"promjenjen status narudžbe",Toast.LENGTH_SHORT).show()
        dismiss()
    }
}