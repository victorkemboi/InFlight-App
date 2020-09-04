package com.mes.user_app.data.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mes.user_app.data.model.core.User
import com.mes.user_app.utils.DateTypeConverter
import com.mes.user_app.utils.UtilityClass
import java.util.*

@Entity(tableName = "user")
@TypeConverters(DateTypeConverter::class)
data class UserDb(
        @ColumnInfo(name = "addedOn")
        var addedOn: Date = UtilityClass.getCurrentDateTime(),

        @ColumnInfo(name = "avatar")
        override var avatar : String = "",

        @ColumnInfo(name = "name")
        override var name : String = "",

        @ColumnInfo(name = "email")
        override var email: String = "",

        @ColumnInfo(name = "id")
        override var id: String = "",

        @ColumnInfo(name = "createdLocally")
        override var createdLocally: Boolean = false,

        @ColumnInfo(name = "syncStatus")
        override var syncStatus: String = "None",

        ): User{
        @PrimaryKey(autoGenerate = true)
        var userId: Int? = null
}


