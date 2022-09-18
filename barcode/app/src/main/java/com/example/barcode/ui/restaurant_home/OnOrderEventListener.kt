package com.example.barcode.ui.restaurant_home

import com.example.barcode.model.Order

interface OnOrderEventListener {
    fun onOrderSelected(order: Order?)
    fun onOrderLongPress(order: Order?) : Boolean
}