package com.mes.user_app.data.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mes.user_app.data.model.core.UserB
import com.mes.user_app.utils.DateTypeConverter
import com.mes.user_app.utils.UtilityClass
import java.util.*

@Entity(tableName = "user")
@TypeConverters(DateTypeConverter::class)
data class User(
        @ColumnInfo(name = "addedOn")
        var addedOn: Date = UtilityClass.getCurrentDateTime(),

        @SerializedName("avatar")
        @ColumnInfo(name = "avatar")
        override var avatar : String = "",

        @SerializedName("name")
        @ColumnInfo(name = "name")
        override var name : String = "",

        @SerializedName("email")
        @ColumnInfo(name = "email")
        override var email: String = "",

        @SerializedName("id")
        @ColumnInfo(name = "id")
        override var id: String = "",

        @ColumnInfo(name = "createdLocally")
        override var createdLocally: Boolean = false,

        @ColumnInfo(name = "syncStatus")
        override var syncStatus: String = "None",

        @ColumnInfo(name = "syncedOn")
        var syncedOn: Date? = null,

        ): UserB{
        @PrimaryKey(autoGenerate = true)
        var userId: Int? = null
}


