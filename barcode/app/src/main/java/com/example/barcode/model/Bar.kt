package com.example.barcode.model

    data class Bar(
        var id: String="",
        var name: String="name",
        var email: String="email",
        var imageUrl:String="imageUrl",
        var address: String="",
        var articles: List<Article> = listOf()
    ){
    }
