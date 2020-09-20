package com.mes.inflight_mag.utils

import android.os.Build
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*

object UtilityClass {
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getCurrentDateAsString(): String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().toString()
        } else {

            DateFormat.getDateTimeInstance().format(getCurrentDateTime())
        }
    }
}