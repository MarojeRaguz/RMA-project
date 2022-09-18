package com.example.barcode.data

import com.example.barcode.model.Article
import com.example.barcode.model.Bar

interface BarRepository {
    fun getAllBars():List<Bar>
    fun getBarsArticle(barId: String): List<Article>
    fun getBarByBarId(barId: String): Bar
    fun getBarByBarEmail(email: String):Bar
    fun saveBarsToSharedPreferences()
}