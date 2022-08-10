package com.example.guidomia

import android.content.Context
import android.content.SharedPreferences

/**
    For the bonus question. Helper for shared preferences. Used to save data internally.
    I decided to do the bonus at the same time as Level 1 because it greatly helps to link the image with the right car
    References: https://www.digitalocean.com/community/tutorials/android-sharedpreferences-kotlin
                https://gist.github.com/JCarlosR/7f457aeebd9c3670933221e97116c4c6
 */

/**
 *  TODO:
 *    1. Testing
 */
object PreferenceHelper {

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = this.edit()
        operation(editMe)
        editMe.apply()
    }

    /**
     * puts a value for the given [key].
     */
    operator fun SharedPreferences.set(key: String, value: Any?)
            = when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * finds a preference based on the given [key].
     * [T] is the type of value
     * @param defaultValue optional defaultValue - will take a default defaultValue if it is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T
            = when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}