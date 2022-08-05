package com.example.barcode.data

import com.example.barcode.model.Article
import com.example.barcode.model.Bar
import java.util.function.LongFunction

interface BarRepository {

    fun getAllBars(): List<Bar>
    fun getBarsArticle(barId: String): List<Article>
    fun getBar(barId: String): Bar

}