package com.example.barcode.data

import com.example.barcode.model.Article
import com.example.barcode.model.Order
import com.example.barcode.model.OrderStatus

interface OrderRepository {

    fun makeOrder(articles:List<Article>, table: Int, barId: String)
    fun changeOrderStatus(orderId:String, orderStatus: OrderStatus)
    fun savedOrdersToSharedPreferences(email:String)
    fun getOrders():List<Order>
}