package com.example.barcode.ui.restaurant_home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcode.R
import com.example.barcode.databinding.ItemOrderedItemBinding
import com.example.barcode.model.Order

class ViewOrderDialogAdapter(private val order: Order): RecyclerView.Adapter<ViewOrderDialogAdapter.ViewOrderDialogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewOrderDialogViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ordered_item,parent,false)
        return ViewOrderDialogViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewOrderDialogViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return order.articles.count()
    }

    inner class ViewOrderDialogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val article = order.articles[position]
            val binding = ItemOrderedItemBinding.bind(itemView)
            binding.tvPrice.text = "${article.price.toString()} kn"
            binding.tvCount.text = "${article.count.toString()}x"
            binding.tvItemTotal.text = "${article.price* article.count} kn"
            binding.tvName.text = article.name
        }
    }
}

