package com.example.barcode.model

class Order {

    data class Order(
        var id: Long,
        var bar_id:Long,
        var table: Int,
        var articles: List<Article>,
        var orderTime: String,
        var status: String
    ){

    }
}