package com.example.barcode.ui.menu_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.data.BarRepository
import com.example.barcode.data.BarRepositoryImpl
import com.example.barcode.databinding.ActivityMenuListBinding


class MenuListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuListBinding
    private lateinit var adapter: MenuAdapter
    var barRepository: BarRepository = BarRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvMenuList.layoutManager = LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,false
        )
        adapter = MenuAdapter()
        binding.rvMenuList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        var barId = intent.getStringExtra("barId")
        if (barId != null)
        adapter.setArticles(barRepository.getBarsArticle(barId))
    }


}