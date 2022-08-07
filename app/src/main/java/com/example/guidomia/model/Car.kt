package com.example.guidomia.model

import android.util.Log
import com.example.guidomia.R
import org.json.JSONArray
import java.lang.reflect.Field

/**
 *   Car class to hold
 *   the [model],
 *   the [make],
 *   the [customerPrice],
 *   the [marketPrice],
 *   the [rating],
 *   a [prosList] and
 *   a [consList] of a car
 */
data class Car(
    var model: String,
    var make: String,
    val customerPrice: Double,
    val marketPrice: Double,
    var rating: Int,
    val prosList: JSONArray,
    val consList: JSONArray,
    val imageName: String
) {
    /**
     *   Initialize the prices to be in $k format, initialize the dynamic lists for pros and cons, set the image ID to display
     */
    init {
        var customerPriceString = "${customerPrice % 1000}k"
        var marketPriceString = "${marketPrice % 1000}k"
        var prosMutableList: MutableList<String> = setMutableList(prosList)
        var consMutableList: MutableList<String> = setMutableList(consList)
    }

    private fun setMutableList(arr: JSONArray): MutableList<String> {
        var array: MutableList<String> = mutableListOf()
        for(i in 0 until arr.length()) {
            array.add(arr[i] as String)
        }
        return array
    }


}