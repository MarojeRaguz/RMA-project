package com.example.barcode.ui.bars_list

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcode.R
import com.example.barcode.databinding.ItemBarBinding
import com.example.barcode.model.Article
import com.example.barcode.model.Bar
import com.squareup.picasso.Picasso

class BarAdapter:RecyclerView.Adapter<BarViewHolder>() {

    private val bars = mutableListOf<Bar>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bar,parent,false)
        return BarViewHolder(view)
    }
    fun setBars(bars: List<Bar>) {
        this.bars.clear()
        this.bars.addAll(bars)
        this.notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: BarViewHolder, position: Int) {
        val bar = bars[position]
        holder.bind(bar)
    }

    override fun getItemCount(): Int {
        return bars.count()
    }
}
class BarViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bind(bar: Bar){
        val binding = ItemBarBinding.bind(itemView)
        binding.tvName.text = bar.name
        Picasso.get().load(bar.imageUrl).into(binding.ivBar)
    }
}
