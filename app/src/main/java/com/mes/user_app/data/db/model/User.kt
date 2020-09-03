package com.mes.user_app.data.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mes.user_app.utils.DateTypeConverter
import com.mes.user_app.utils.UtilityClass
import java.util.*

@Entity(tableName = "user")
@TypeConverters(DateTypeConverter::class)
data class User(
        @ColumnInfo(name = "addedOn")
        var addedOn: Date = UtilityClass.getCurrentDateTime(),

        @ColumnInfo(name = "avatar")
        var avatar : String = "",

        @ColumnInfo(name = "name")
        var name : String = "",

        @ColumnInfo(name = "email")
        var email: String = "",

        @ColumnInfo(name = "id")
        var id: String = "",

        @ColumnInfo(name = "createdLocally")
        var createdLocally: Boolean = false,

        @ColumnInfo(name = "syncStatus")
        var syncStatus: String = "None",


        ){
        @PrimaryKey(autoGenerate = true)
        var userId: Int? = null
}


