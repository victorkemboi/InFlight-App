package com.mes.inflight_mag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mes.inflight_mag.utils.DateTypeConverter
import com.mes.inflight_mag.utils.UtilityClass
import java.util.*

@Entity(tableName = "customer")
@TypeConverters(DateTypeConverter::class)
data class Customer(
    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    var userId: String = "",

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name : String = "",

    @SerializedName("mobile_number")
    @ColumnInfo(name = "mobile_number")
    var monileNumber: String = "",

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String = "",

    @SerializedName("device_id")
    @ColumnInfo(name = "device_id")
    var deviceId: String = "",

    @ColumnInfo(name = "addedOn")
    var addedOn: Date = UtilityClass.getCurrentDateTime(),

    @SerializedName("token")
    var token : String = "",

    @ColumnInfo(name = "createdLocally")
    var createdLocally: Boolean = false,

    @ColumnInfo(name = "syncStatus")
    var syncStatus: String = "None",

    @ColumnInfo(name = "syncedOn")
    var syncedOn: Date? = null,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}