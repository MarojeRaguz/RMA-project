package com.example.barcode.ui.restaurant_home.restaurant_home_fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barcode.data.BarRepository
import com.example.barcode.data.BarRepositoryImpl
import com.example.barcode.databinding.FragmentRestaurantHomeBinding
import com.example.barcode.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RestaurantHomeFragment: Fragment(), OnOrderEventListener {

    private lateinit var binding: FragmentRestaurantHomeBinding
    private var barRepository: BarRepository = BarRepositoryImpl()
    private var user = FirebaseAuth.getInstance().currentUser
    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("orders")
    private lateinit var adapter: RestaurantHomeAdapter
    private lateinit var orders: ArrayList<Order>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantHomeBinding.inflate(layoutInflater)
        binding.rvRestaurantHome.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvRestaurantHome.adapter
        orders = arrayListOf()
        updateData()
        return binding.root
    }

    private fun updateData() {
        val email = user!!.email.toString()
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
                adapter = RestaurantHomeAdapter(orders)
                adapter.onOrderEventListener = this@RestaurantHomeFragment
                binding.rvRestaurantHome.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    override fun onOrderSelected(order: Order?) {
        if (order != null){
            val dialog = ViewOrderDialog(order)
            dialog.show(childFragmentManager,"view order dialog")
        }else {
            Log.d("order not found","order not found")
        }
    }

    override fun onOrderLongPress(order: Order?): Boolean {
        return if (order != null){
            val dialog = ChangeOrderStatusDialog(order)
            dialog.show(childFragmentManager,"change order status dialog")
            true
        }else {
            Log.d("order not found","order not found")
            false
        }
    }
}