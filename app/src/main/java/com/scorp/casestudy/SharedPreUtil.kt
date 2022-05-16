package com.scorp.casestudy

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import java.lang.ClassCastException

/**
 * @author Mucahid Dogan
 * @since 16.05.22
 *
 * mucahidd3@gmail.com
 */
object SharedPreUtil {

    fun putLong(key: String, value: Long, context: Context) {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String, defValue: Long, context: Context) : Long {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return try {
            preferences.getLong(key, defValue)
        } catch (e: ClassCastException) {
            defValue
        }
    }

}