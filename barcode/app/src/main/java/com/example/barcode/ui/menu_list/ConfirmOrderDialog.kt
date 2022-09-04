package com.example.barcode.ui.menu_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.databinding.DialogConfirmOrderBinding
import com.example.barcode.model.Article
import java.util.function.Consumer


class ConfirmOrderDialog: DialogFragment() {

    private lateinit var binding: DialogConfirmOrderBinding
    private lateinit var adapter: ConfirmOrderDialogAdapter
    private lateinit var articles: List<Article>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogConfirmOrderBinding.inflate(layoutInflater)
        setupRecyclerView()
        binding.tvTotal.text = getTotal()
        return binding.root
    }

    private fun getTotal(): String {
        var total = 0.0
        articles.stream().forEach(Consumer { total += it.count * it.price })
        return total.toString()
    }

    private fun setupRecyclerView() {
        binding.rvOrder.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,false
        )
        adapter = ConfirmOrderDialogAdapter()
        binding.rvOrder.adapter = adapter
    }
    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setArticles(articles)
    }

    fun setArticles(orderArticles: List<Article>) {
        articles = orderArticles
    }


}