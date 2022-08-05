package com.example.barcode.model

    data class Bar(
        var id: String="",
        var name: String="name",
        var email: String="email",
        var imageUrl:String="imageUrl",
        var lat: Int=2,
        var lang: Int=2,
        var articles: List<Article> = listOf()
    ){

    }
