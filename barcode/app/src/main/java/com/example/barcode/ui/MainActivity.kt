package com.example.barcode.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barcode.data.BarRepository
import com.example.barcode.data.BarRepositoryImpl
import com.example.barcode.databinding.ActivityMainBinding
import com.example.barcode.model.Article
import com.example.barcode.model.Bar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var barRepository: BarRepository = BarRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barRepository.saveBarsToSharedPreferences()
    }
}