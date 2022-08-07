package com.example.guidomia

import android.content.Context
import android.content.SharedPreferences

/*
    For the bonus question. Helper for shared preferences. Used to save data internally.
    I decided to do the bonus at the same time as Level 1 because it greatly helps to link the image with the right car
    Reference: https://www.digitalocean.com/community/tutorials/android-sharedpreferences-kotlin
 */
/*
    TODO:
     1. Testing
     2. Logic to match car with image
 */
object PreferenceHelper {

    val CAR_NAME = "CAR"
    val CAR_IMAGE_ID = "IMAGE"

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.carName
        get() = getString(CAR_NAME, "")
        set(value) {
            editMe {
                it.putString(CAR_NAME, value)
            }
        }

    var SharedPreferences.password
        get() = getInt(CAR_IMAGE_ID, 0)
        set(value) {
            editMe {
                it.putInt(CAR_IMAGE_ID, value)
            }
        }

    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}