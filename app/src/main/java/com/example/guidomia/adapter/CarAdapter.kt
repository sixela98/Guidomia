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

class CarAdapter(private val context: Context, private val dataset: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val carImage: ImageView = view.findViewById(R.id.carImageView)
        private val carName: TextView = view.findViewById(R.id.carNameTextView)
        private val price: TextView = view.findViewById(R.id.carPriceTextView)
        private val rating: RatingBar = view.findViewById(R.id.ratingBar)

        fun show(car: Car) {
            carImage.setImageResource(car.imageId)
            carName.text = car.model
            val priceString = "Price: ${car.customerPriceString}"
            price.text = priceString
            rating.rating = car.rating.toFloat()
            println(car.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_view, parent, false)
        return CarViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item: Car = dataset[position]
        println(item.toString())
        holder.show(item)
    }

    override fun getItemCount() = dataset.size


}