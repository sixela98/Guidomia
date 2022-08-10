package com.example.guidomia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.R
import com.example.guidomia.model.Car

/**
 *  Car adapter to display the RecycleView.
 *  Show() function sets up the view's components with the car data
 *  onCreateViewHolder, onBindViewHolder and getItemCount are overridden with their respective implementation
 */

class ProConAdapter(private val context: Context, private val dataset: MutableList<String>) : RecyclerView.Adapter<ProConAdapter.ProConViewHolder>() {

    class ProConViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val proConTextView: TextView = view.findViewById(R.id.proConTextView)

        fun show(info: String) {
            proConTextView.text = info
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProConViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pros_cons_view, parent, false)
        return ProConViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProConViewHolder, position: Int) {
        val item: String = dataset[position]
        holder.show(item)
    }

    override fun getItemCount() = dataset.size


}