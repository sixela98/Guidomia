package com.example.guidomia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.adapter.CarAdapter
import com.example.guidomia.data.Datasource


class MainActivity : AppCompatActivity() {
    // Car adapter to be used for RecycleView
    private lateinit var adapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the data set from json file and link it with the adapter
        val dataSet = Datasource().loadData(this)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CarAdapter(this, dataSet)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

}


/**
 * TODO:
 * 1. Fix UI (gray background)
 * 2. TESTING!!!!
 */



