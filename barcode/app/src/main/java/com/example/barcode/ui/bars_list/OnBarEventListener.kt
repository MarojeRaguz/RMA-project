package com.example.barcode.ui.bars_list

import com.example.barcode.model.Bar

interface OnBarEventListener {
    fun onBarSelected(bar: Bar)
}