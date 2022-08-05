package com.example.barcode.ui.bars_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.data.BarRepository
import com.example.barcode.data.BarRepositoryImpl
import com.example.barcode.databinding.FragmentBarsListBinding


class BarsListFragment: Fragment() {

    private lateinit var binding: FragmentBarsListBinding
    private lateinit var adapter: BarAdapter
    private var barRepository: BarRepository = BarRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBarsListBinding.inflate(layoutInflater)
        setupRecyclerView()
        binding.ivScanBarcode.setOnClickListener { showScanFragment() }
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
        updateData()
    }

    private fun updateData() {
        val bars = barRepository.getAllBars()
        adapter.setBars(bars)
    }

    private fun showScanFragment() {
        val action = BarsListFragmentDirections.actionBarsListFragmentToScanFragment()
        findNavController().navigate(action)
    }

}