package com.example.barcode.data

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.barcode.Barcode
import com.example.barcode.model.Article
import com.example.barcode.model.Bar
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BarRepositoryImpl: BarRepository {
    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("bars")

    override fun saveBarsToSharedPreferences(){
        var context = Barcode.application
        var bars:MutableList<Bar> = mutableListOf()
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                bars.clear()
                for (snapshot in dataSnapshot.children){
                    var bar = snapshot.getValue(Bar::class.java)
                    if(bar != null){
                        Log.w(TAG, "Adding bar $bar")
                        bars.add(bar)
                    }
                }
                val sharedPreferences : SharedPreferences = context.getSharedPreferences("barCode",Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                var gson = Gson()
                var json = gson.toJson(bars)
                editor.putString("barsList",json)
                editor.commit()
            }
            override fun onCancelled(error:DatabaseError) {
                Log.i(TAG, "Failed to read value.", error.toException())
            }
        })
    }
    override fun getAllBars(): List<Bar> {
       return this.getBarsFromSharedPreferences()
    }

    override fun getBarsArticle(barId: String): List<Article> {
        var bars = this.getAllBars()
        var bar =  bars.find { it.id == barId }
        return bar?.articles ?: emptyList()
    }
    override fun getBarByBarId(barId: String): Bar {
        var bars = this.getAllBars()
        return bars.find { it.id == barId } ?: error("not found")
    }

    override fun getBarByBarEmail(email: String): Bar {
        var bars = this.getAllBars()
        return bars.find { it.email == email } ?: error("not found")
    }

    private fun getBarsFromSharedPreferences():List<Bar>{
        var context = Barcode.application
        val sharedPreference =  context.getSharedPreferences("barCode", Context.MODE_PRIVATE)
        return if(sharedPreference != null){
            var gson = Gson()
            var barJson=sharedPreference.getString("barsList","")
            gson.fromJson(barJson,object : TypeToken<List<Bar>>(){}.type)
        }else{
            emptyList()
        }
    }
}