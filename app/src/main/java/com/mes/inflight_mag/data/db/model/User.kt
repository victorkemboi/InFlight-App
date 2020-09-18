package com.mes.inflight_mag.data.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mes.inflight_mag.utils.DateTypeConverter
import com.mes.inflight_mag.utils.UtilityClass
import java.util.*

@Entity(tableName = "user")
@TypeConverters(DateTypeConverter::class)
data class User(
        @ColumnInfo(name = "addedOn")
        var addedOn: Date = UtilityClass.getCurrentDateTime(),

        @SerializedName("avatar")
        @ColumnInfo(name = "avatar")
        var avatar : String = "",

        @SerializedName("name")
        @ColumnInfo(name = "name")
        var name : String = "",

        @SerializedName("email")
        @ColumnInfo(name = "email")
        var email: String = "",

        @SerializedName("id")
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "createdLocally")
        var createdLocally: Boolean = false,

        @ColumnInfo(name = "syncStatus")
        var syncStatus: String = "None",

        @ColumnInfo(name = "syncedOn")
        var syncedOn: Date? = null,

        ){
        @PrimaryKey(autoGenerate = true)
        var userId: Int? = null
}


