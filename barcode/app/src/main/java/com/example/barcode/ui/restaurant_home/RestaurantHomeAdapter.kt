package com.example.barcode.ui.restaurant_home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcode.R
import com.example.barcode.databinding.ItemOrderBinding

import com.example.barcode.model.Order
import java.util.function.Consumer

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

            var binding = ItemOrderBinding.bind(itemView)
            var order = rvOrders[position]
            var total = 0.0
            binding.tvOrderId.text = order.id
            binding.tvTable.text = order.table.toString()
            order.articles.stream().forEach { total += it.count * it.price }
            binding.tvTotal.text = total.toString()
        }
    }

}

