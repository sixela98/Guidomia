package com.example.guidomia

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.adapter.CarAdapter
import com.example.guidomia.data.Datasource
import com.example.guidomia.model.Car
import org.json.JSONArray
import java.io.IOException
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
 * 1. Fix price showing 0.0 instead of the right value
 * 2. Fix rating
 * 3. Fix UI (gray background)
 * 4. TESTING!!!!
 */



