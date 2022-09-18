package com.example.barcode.ui.restaurant_home.restaurant_home_fragment

import com.example.barcode.model.Order

interface OnOrderEventListener {
    fun onOrderSelected(order: Order?)
    fun onOrderLongPress(order: Order?) : Boolean
}