package com.example.guidomia.model

import org.json.JSONArray

/**
 *   Car class to hold
 *   the [model],
 *   the [make],
 *   the [customerPrice],
 *   the [marketPrice],
 *   the [rating],
 *   the [prosList],
 *   the [consList],
 *   the [name],
 *   the [imageId] of a car.
 */
data class Car(
    var model: String,
    var make: String,
    val customerPrice: Double,
    val marketPrice: Double,
    var rating: Int,
    val prosList: JSONArray,
    val consList: JSONArray,
    val name: String,
    val imageId: Int
) {
    /**
     *   Initialize the prices to be in $k format, initialize the dynamic lists for pros and cons, set the image ID to display
     */
    var customerPriceString: String = "${customerPrice/1000.0}k"
    var marketPriceString = "${marketPrice/1000.0}k"
    var prosMutableList: MutableList<String> = setMutableList(prosList)
    var consMutableList: MutableList<String> = setMutableList(consList)

    private fun setMutableList(arr: JSONArray): MutableList<String> {
        var array: MutableList<String> = mutableListOf()
        for(i in 0 until arr.length()) {
            array.add(arr[i] as String)
        }
        return array
    }


}