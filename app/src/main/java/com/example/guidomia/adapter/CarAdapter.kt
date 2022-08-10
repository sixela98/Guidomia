package com.example.guidomia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.R
import com.example.guidomia.data.Datasource
import com.example.guidomia.model.Car

/**
 *  Car adapter to display the RecycleView.
 *  Show() function sets up the view's components with the car data
 *  onCreateViewHolder, onBindViewHolder and getItemCount are overridden with their respective implementation
 */

class CarAdapter(private val context: Context, private val dataset: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private lateinit var mListener: onItemClickListener
    var itemToExpand: Int = 0

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CarViewHolder(private val view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        private val carImage: ImageView = view.findViewById(R.id.carImageView)
        private val carName: TextView = view.findViewById(R.id.carNameTextView)
        private val price: TextView = view.findViewById(R.id.carPriceTextView)
        private val rating: RatingBar = view.findViewById(R.id.ratingBar)
        private val expandedView: View = view.findViewById(R.id.carExpandedView)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun show(car: Car) {
            carImage.setImageResource(car.imageId)
            carName.text = car.name
            val priceString = "Price: ${car.customerPriceString}"
            price.text = priceString
            rating.rating = car.rating.toFloat() - 1.0F
        }

        fun expand(position: Int, itemToExpand: Int) {
            if(position == itemToExpand) {
                expandedView.visibility = View.VISIBLE
            }else {
                expandedView.visibility = View.GONE
            }
        }

        // Show the pro and con list. Could be separated in a function showList to not duplicate the code for pro and con
        fun showExpand(car: Car, context: Context) {
            val listOfPros = car.prosMutableList
            val listOfCons = car.consMutableList
            val proRecyclerView: RecyclerView = expandedView.findViewById(R.id.proRecyclerView)
            val conRecyclerView: RecyclerView = expandedView.findViewById(R.id.conRecyclerView)
            proRecyclerView.layoutManager = LinearLayoutManager(context)
            conRecyclerView.layoutManager = LinearLayoutManager(context)
            val proAdapter = ProConAdapter(context, listOfPros)
            val conAdapter = ProConAdapter(context, listOfCons)
            proRecyclerView.adapter = proAdapter
            conRecyclerView.adapter = conAdapter
            proRecyclerView.setHasFixedSize(true)
            conRecyclerView.setHasFixedSize(true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_view, parent, false)
        return CarViewHolder(adapterLayout, mListener)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item: Car = dataset[position]
        holder.show(item)
        holder.expand(position, itemToExpand)
        if(position == itemToExpand) {
            holder.showExpand(item, context)
        }
    }

    override fun getItemCount() = dataset.size


}