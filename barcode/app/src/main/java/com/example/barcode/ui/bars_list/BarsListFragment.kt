package com.example.barcode.ui.bars_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.databinding.FragmentBarsListBinding
import com.example.barcode.model.Article
import com.example.barcode.model.Bar

class BarsListFragment: Fragment() {

    private lateinit var binding: FragmentBarsListBinding
    private lateinit var adapter: BarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBarsListBinding.inflate(layoutInflater)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvBars.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = BarAdapter()
        binding.rvBars.adapter=adapter
    }

    override fun onResume() {
        super.onResume()
        updateData();
    }

    private fun updateData() {
        val bars = mutableListOf<Bar>()
        val article = Article("pivo",22.34,2)
        val articles = mutableListOf<Article>()
        articles.add(article)
        val bar = Bar(1232,"broko","e","https://imageproxy.wolt.com/venue/6214c9ed4305715646ebba42/b32d3f0a-973c-11ec-8399-ce2cd6617ed7_broko__6_of_10_.jpg",22,22,articles)
        bars.add(bar)
        bars.add(bar)
        bars.add(bar)
        adapter.setBars(bars)
    }
}