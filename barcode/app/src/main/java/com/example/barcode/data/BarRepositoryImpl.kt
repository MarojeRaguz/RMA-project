package com.example.barcode.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.barcode.model.Article
import com.example.barcode.model.Bar
import com.google.firebase.database.*

class BarRepositoryImpl: BarRepository {

    private val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("bars")
    override fun getAllBars(): List<Bar> {
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
            }
            override fun onCancelled(error:DatabaseError) {
                Log.i(TAG, "Failed to read value.", error.toException())
            }
        })
        return bars
    }

    override fun getBarsArticle(barId: String): List<Article> {
        var articles: MutableList<Article> = mutableListOf()
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children){
                    var bar = snapshot.getValue(Bar::class.java)
                    if(bar?.id == barId){
                        articles = bar.articles as MutableList<Article>
                        break
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        Log.i(TAG,"found articles $articles")
        return articles
    }
    override fun getBar(barId: String): Bar {
        var bar = Bar()
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children){
                    var currentBar = snapshot.getValue(Bar::class.java)
                    if(currentBar?.id == barId){
                        bar=currentBar
                        Log.i(TAG,"found bar $bar")
                        break
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())            }
        })

        return bar
    }
}