package com.example.barcode.ui.menu_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.data.OrderRepository
import com.example.barcode.data.OrderRepositoryImpl
import com.example.barcode.databinding.DialogConfirmOrderBinding
import com.example.barcode.model.Article
import java.util.function.Consumer


class ConfirmOrderDialog(
    var table: Int,
    var barId: String
): DialogFragment() {

    private lateinit var binding: DialogConfirmOrderBinding
    private lateinit var adapter: ConfirmOrderDialogAdapter
    private lateinit var articles: List<Article>

    private var orderRepository: OrderRepository = OrderRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogConfirmOrderBinding.inflate(layoutInflater)
        setupRecyclerView()
        binding.tvTotal.text = "UKUPNO: "+ getTotal() + "kn"
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnOrder.setOnClickListener {
            makeOrder()
        }
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


    private fun makeOrder() {
        orderRepository.makeOrder(articles,table,barId)
        Toast.makeText(context,"narudzba je uspjesno napravljena",Toast.LENGTH_SHORT).show()
        dismiss()
    }


}