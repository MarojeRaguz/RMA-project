package com.example.barcode.ui.menu_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barcode.R
import com.example.barcode.databinding.ItemOrderItemBinding
import com.example.barcode.model.Article


class MenuAdapter: RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var rvArticles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_item,parent,false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return rvArticles.count()
    }

    fun setArticles(articles: List<Article>){
        rvArticles = articles as MutableList<Article>
    }

    fun getArticles():List<Article>{
        return rvArticles.filter { it.count > 0 }
    }

    inner class MenuViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int){
            val article = rvArticles[position]
            val binding = ItemOrderItemBinding.bind(itemView)
            binding.tvArticleName.text = article.name
            binding.tvTotal.text = article.count.toString()
            binding.btnMinus.setOnClickListener {
                if (article.count > 0){
                    article.count--
                }
                notifyItemChanged(position)
            }
            binding.btnPlus.setOnClickListener {
                article.count++
                notifyItemChanged(position)
            }
        }
    }
}

