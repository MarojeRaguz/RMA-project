package com.example.barcode.utils

import android.content.res.Resources
import com.example.barcode.R
import com.example.barcode.model.OrderStatus

fun Resources.getColorResource(orderStatus: OrderStatus): Int{
    return when(orderStatus){
        OrderStatus.DELIVERED -> R.color.delivered
        OrderStatus.ORDERED -> R.color.background2
        else -> R.color.background2
    }
}