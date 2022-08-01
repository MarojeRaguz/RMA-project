package com.example.barcode.model

    data class Bar(
        var id: Long,
        var name: String,
        var email: String,
        var imageUrl:String,
        var lat: Int,
        var lang: Int,
        var articles: List<Article>
    ){

    }
