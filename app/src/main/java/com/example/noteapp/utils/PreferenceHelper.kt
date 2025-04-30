package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreferences: SharedPreferences

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var text: String?
        get() = sharedPreferences.getString("text", "")
        set(value:String?) = sharedPreferences.edit().putString("text", value).apply()

    var isOnBoardShown: Boolean
        get() = sharedPreferences.getBoolean("isShown", true)
        set(value) = sharedPreferences.edit().putBoolean("isShown", value).apply()

    var layoutManager : Int
        get() = sharedPreferences.getInt("LAYOUT_MANAGER", 0)
        set(value) = sharedPreferences.edit().putInt("LAYOUT_MANAGER", value).apply()

    var isAuth : Boolean
        get() = sharedPreferences.getBoolean("IS_AUTH", true)
        set(value) = sharedPreferences.edit().putBoolean("IS_AUTH", value).apply()
}