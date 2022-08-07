package com.example.guidomia.data

import android.content.Context
import com.example.guidomia.R
import com.example.guidomia.model.Car
import org.json.JSONArray
import java.io.IOException
import java.lang.reflect.Field

class Datasource {

    fun loadData(context: Context): ArrayList<Car> {
        var carArrayList: ArrayList<Car> = ArrayList()
        val carString: String = getJsonDataFromAsset(context, "car_list.json") ?: ""
        val carArray = JSONArray(carString)
        val drawablesFields: Array<Field> = R.drawable::class.java.fields
        for (i in 0 until carArray.length()) {
            val obj = carArray.getJSONObject(i)
            val model = obj["model"] as String
            val make = obj["make"] as String
            val words: ArrayList<String> = ArrayList()
            for (word in model.split("\\s".toRegex()).toTypedArray()) {
                words.add(word)
            }
            for (word in make.split("\\s".toRegex()).toTypedArray()) {
                words.add(word)
            }
            for (field in drawablesFields) {
                for (word in words) {
                    if (field.name.contains(word.lowercase())) {

                        carArrayList.add(
                            Car(
                                obj.getString("model"),
                                obj.getString("make"),
                                obj.getDouble("customerPrice"),
                                obj.getDouble("marketPrice"),
                                obj.getInt("rating"),
                                obj.getJSONArray("prosList"),
                                obj.getJSONArray("consList"),
                                context.resources.getIdentifier(field.name, "drawable", context.packageName)
                            )
                        )
                        break
                    }

                }
            }
        }
        return carArrayList
    }

    //TODO: Improve the logic
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