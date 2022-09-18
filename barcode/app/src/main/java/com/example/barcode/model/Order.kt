package com.example.barcode.model

    enum class OrderStatus{
        ORDERED,DELIVERED,DELETED
    }

    data class Order(
        var id: String="",
        var bar_id:String="",
        var table: Int = 0,
        var articles: List<Article> = listOf(),
        var orderTime: String = "",
        var status: OrderStatus = OrderStatus.ORDERED
    ){
    }
