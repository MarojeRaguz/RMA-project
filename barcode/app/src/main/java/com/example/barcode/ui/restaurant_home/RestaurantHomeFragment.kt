package com.example.barcode.ui.restaurant_home

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.data.BarRepository
import com.example.barcode.data.BarRepositoryImpl
import com.example.barcode.data.OrderRepository
import com.example.barcode.data.OrderRepositoryImpl
import com.example.barcode.databinding.FragmentRestaurantHomeBinding
import com.example.barcode.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson

class RestaurantHomeFragment: Fragment(),OnOrderEventListener {

    private lateinit var binding: FragmentRestaurantHomeBinding
    private var barRepository: BarRepository = BarRepositoryImpl()
    private var user = FirebaseAuth.getInstance().currentUser
    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("orders")
    private lateinit var orders: ArrayList<Order>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantHomeBinding.inflate(layoutInflater)
        binding.rvRestaurantHome.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        orders = arrayListOf<Order>()
        updateData()
        return binding.root
    }

    private fun updateData() {
        var email = user!!.email.toString()
        val barId = barRepository.getBarByBarEmail(email).id
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                orders.clear()
                for (snapshot in dataSnapshot.children){
                    val order = snapshot.getValue(Order::class.java)
                    if (order != null && order.bar_id == barId){
                        orders.add(order)
                    }
                }
                binding.rvRestaurantHome.adapter = RestaurantHomeAdapter(orders)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    override fun onOrderSelected(order: Order?) {
        TODO("Not yet implemented")
    }

    override fun onOrderLongPress(order: Order?): Boolean {
        TODO("Not yet implemented")
    }
}