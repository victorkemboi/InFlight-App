package com.mes.user_app.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs (context: Context){
    private var settings: SharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    var token: String?
        get() = settings.getString("token","")
        set(token) {
            val editor = settings.edit()
            editor.putString("token", token)
            editor.apply()
        }
}