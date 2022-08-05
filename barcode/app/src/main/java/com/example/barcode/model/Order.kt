package com.example.barcode.model

    enum class OrderStatus{
        ORDERED,DELIVERED
    }

    data class Order(
        var id: String,
        var bar_id:String,
        var table: Int,
        var articles: List<Article>,
        var orderTime: String,
        var status: OrderStatus
    ){

    }
