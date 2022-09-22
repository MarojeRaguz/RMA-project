package com.example.barcode.ui.restaurant_home.restaurant_home_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcode.R
import com.example.barcode.databinding.ItemOrderBinding

import com.example.barcode.model.Order
import com.example.barcode.utils.getColorResource

class RestaurantHomeAdapter(private val rvOrders : ArrayList<Order>): RecyclerView.Adapter<RestaurantHomeAdapter.RestaurantHomeViewHolder>() {

    var onOrderEventListener: OnOrderEventListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return RestaurantHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantHomeViewHolder, position: Int) {
        val order = rvOrders[position]
        holder.bind(position)
        onOrderEventListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onOrderSelected(order) }
            holder.itemView.setOnLongClickListener {listener.onOrderLongPress(order)}
        }
    }

    override fun getItemCount(): Int {
        return rvOrders.count()
    }

    inner class RestaurantHomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int){

            val binding = ItemOrderBinding.bind(itemView)
            val order = rvOrders[position]
            var total = 0.0
            binding.tvOrderTime.text = "Vrijeme narudžbe:\n${order.orderTime}"
            binding.tvTable.text = "Broj stola:\n${order.table}"
            order.articles.stream().forEach { total += it.count * it.price }
            binding.tvTotal.text = "Ukupno:\n$total kn"
            binding.llItemOrder.setBackgroundResource(
                binding.llItemOrder.context.resources.getColorResource(order.status
                )
            )

        }
    }

}

