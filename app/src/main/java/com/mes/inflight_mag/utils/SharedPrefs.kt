package com.mes.inflight_mag.utils

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

    var user: String?
        get() = settings.getString("user","")
        set(user) {
            val editor = settings.edit()
            editor.putString("user", user)
            editor.apply()
        }

    var tokenTime: String?
        get() = settings.getString("tokenTime", "")
        set(tokenTime){
            val editor = settings.edit()
            editor.putString("tokenTime", tokenTime)
            editor.apply()
        }

    fun clearSharedPreferenceData(prefsKey: String) {
        settings.edit().remove(prefsKey).apply()
    }
}