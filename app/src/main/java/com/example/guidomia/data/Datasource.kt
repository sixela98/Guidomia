package com.example.guidomia.data

import android.content.Context
import com.example.guidomia.R
import com.example.guidomia.model.Car
import org.json.JSONArray
import java.io.IOException
import java.lang.reflect.Field

/**
 * Class to handle reading the data from json and creating the list of car objects
 */

class Datasource {

    fun loadData(context: Context): ArrayList<Car> {
        var carArrayList: ArrayList<Car> = ArrayList()
        val carString: String = getJsonDataFromAsset(context, "car_list.json") ?: "[]"
        val carArray = JSONArray(carString)
        for (i in 0 until carArray.length()) {
            val obj = carArray.getJSONObject(i)
            carArrayList.add(
                Car(
                    obj.getString("model"),
                    obj.getString("make"),
                    obj.getDouble("customerPrice"),
                    obj.getDouble("marketPrice"),
                    obj.getInt("rating"),
                    obj.getJSONArray("prosList"),
                    obj.getJSONArray("consList"),
                    obj.getString("name"),
                    context.resources.getIdentifier(obj.getString("image"), "drawable", context.packageName)
                )
            )
        }
        return carArrayList
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}