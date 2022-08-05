package com.example.barcode.data

import android.content.ContentValues
import android.util.Log
import com.example.barcode.model.Article
import com.example.barcode.model.Bar
import com.example.barcode.model.Order
import com.example.barcode.model.OrderStatus
import com.google.firebase.database.*
import java.time.LocalDateTime
import java.util.*

class OrderRepositoryImpl(): OrderRepository {
    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("orders")

    override fun makeOrder(articles: List<Article>, table: Int, barId: String) {
        var order = Order(UUID.randomUUID().toString(),barId,table,articles,LocalDateTime.now().toString(),OrderStatus.ORDERED)
        ref.child("orders").setValue(order)
    }

    override fun changeOrderStatus(orderId:String, orderStatus: OrderStatus) {
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children){
                    var order = snapshot.getValue(Order::class.java)
                    order?.status = orderStatus
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to change order status.", error.toException())
            }
        })
    }
}