package com.example.guidomia.model

import android.util.Log
import com.example.guidomia.R
import java.lang.reflect.Field

/*
    Car class to hold
    the [model],
    the [make],
    the [customerPrice],
    the [marketPrice],
    the [rating],
    a [prosList] and
    a [consList] of a car
 */
class Car(var model: String, var make: String, val customerPrice: Double, val marketPrice: Double, var rating: Int, prosList: Array<String>, consList: Array<String>) {
    /*
        Initialize the prices to be in $k format, initialize the dynamic lists for pros and cons, set the image ID to display
     */
    init {
        var customerPriceString = "${customerPrice%1000}k"
        var marketPriceString = "${marketPrice%1000}k"
        var prosMutableList: MutableList<String> = mutableListOf()
        prosMutableList.addAll(prosList)
        var consMutableList: MutableList<String> = mutableListOf()
        consMutableList.addAll(consList)
        var drawableID: Int = getDrawableID()
    }

    private fun getDrawableID(): Int {
        val drawablesFields: Array<Field> = R.drawable::class.java.fields
        for (field in drawablesFields) {
            try {
                Log.i("LOG_TAG", "com.your.project.R.drawable." + field.name)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return -1
    }

}