package com.mes.inflight_mag.utils


import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

object DateTypeConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? = if (timestamp == null) null else Date(timestamp)

    @TypeConverter
    @JvmStatic
    fun toTimestamp(date: Date?): Long? = date?.time


    @JvmStatic
    fun toDateString(date: Date?): String?  {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        return sdf.format(date!!)
        //DateFormat.getDateTimeInstance().format(date)
    }
}

