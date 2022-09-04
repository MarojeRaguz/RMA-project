package com.example.barcode.ui.menu_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcode.R
import com.example.barcode.databinding.ItemOrderedItemBinding
import com.example.barcode.model.Article

class ConfirmOrderDialogAdapter: RecyclerView.Adapter<ConfirmOrderDialogAdapter.OrderViewHolder>() {

    private lateinit var rvArticles : List<Article>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ordered_item,parent,false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OrderViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return rvArticles.count()
    }

    fun setArticles(articles: List<Article>){
        rvArticles = articles
    }
    inner class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

        fun bind(position: Int) {
            var article = rvArticles[position]
            val binding = ItemOrderedItemBinding.bind(itemView)
            binding.tvCount.text = article.count.toString()
            binding.tvPrice.text = article.price.toString()
            binding.tvName.text = article.name
            binding.tvItemTotal.text = (article.price * article.count).toString()
        }
    }
}

