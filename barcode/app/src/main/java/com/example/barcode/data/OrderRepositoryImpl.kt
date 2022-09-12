package com.example.barcode.data

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.barcode.Barcode
import com.example.barcode.model.Article
import com.example.barcode.model.Order
import com.example.barcode.model.OrderStatus
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.util.*

class OrderRepositoryImpl: OrderRepository {

    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("orders")
    private val barRepository: BarRepository = BarRepositoryImpl()
    var context = Barcode.application

    override fun makeOrder(articles: List<Article>, table: Int, barId: String) {
        val order = Order(UUID.randomUUID().toString(),barId,table,articles,LocalDateTime.now().toString(),OrderStatus.ORDERED)
        ref.child(order.id).setValue(order)
    }

    override fun changeOrderStatus(orderId:String, orderStatus: OrderStatus) {
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children){
                    val order = snapshot.getValue(Order::class.java)
                    order?.status = orderStatus
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to change order status.", error.toException())
            }
        })
    }

    override fun savedOrdersToSharedPreferences(email: String){
        var orders: MutableList<Order> = mutableListOf()
        val barId = barRepository.getBarByBarEmail(email).id
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                orders.clear()
                for (snapshot in dataSnapshot.children){
                    val order = snapshot.getValue(Order::class.java)
                    if (order != null && order.bar_id == barId){
                        orders.add(order)
                    }
                }
                Log.i("spremio","spremljeno")
                val sharedPreferences : SharedPreferences = context.getSharedPreferences("barCode",
                    Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                var gson = Gson()
                var json = gson.toJson(orders)
                editor.putString("orderList",json)
                editor.commit()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
    override fun getOrders():List<Order>{
        val sharedPreference =  context.getSharedPreferences("barCode", Context.MODE_PRIVATE)
        if(sharedPreference != null){
            var gson = Gson()
            var orderJson=sharedPreference.getString("orderList","")
            if (orderJson.isNullOrEmpty()){
                return emptyList()
            }else{
                return gson.fromJson(orderJson,object : TypeToken<List<Order>>(){}.type)
            }
        }else{
           return emptyList()
        }
    }
}