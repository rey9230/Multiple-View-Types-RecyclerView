package com.android.storehometest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val info = Utils.getHomeData(this)
        val recyclerView = findViewById<View>(R.id.rvHomeActivity) as RecyclerView
        val linearLayoute = PreLoadingLinearLayoutManager(this)
        linearLayoute.setPages(1)
        val layoutManager: RecyclerView.LayoutManager = linearLayoute
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MainAdapter(info)
    }
}