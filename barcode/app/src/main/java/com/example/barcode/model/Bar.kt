package com.example.barcode.model

class Bar {

    data class Bar(
        var id: Long,
        var name: String,
        var email: String,
        var lat: Int,
        var lang: Int,
        var articles: List<Article>
    ){

    }
}