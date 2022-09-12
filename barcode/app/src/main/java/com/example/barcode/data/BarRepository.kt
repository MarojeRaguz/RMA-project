package com.example.barcode.data

import android.content.Context
import com.example.barcode.model.Article
import com.example.barcode.model.Bar
import com.example.barcode.ui.bars_list.BarAdapter
import java.util.function.LongFunction

interface BarRepository {
    fun getAllBars():List<Bar>
    fun getBarsArticle(barId: String): List<Article>
    fun getBarByBarId(barId: String): Bar
    fun getBarByBarEmail(email: String):Bar
    fun saveBarsToSharedPreferences()
}