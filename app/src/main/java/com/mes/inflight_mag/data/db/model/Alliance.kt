package com.mes.inflight_mag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data class Alliance (

    @SerializedName("alliance_id")
    @ColumnInfo(name = "alliance_id")
    var allianceId: String = "",

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "createdLocally")
    var createdLocally: Boolean = false,

    @ColumnInfo(name = "syncStatus")
    var syncStatus: String = "None",

    @ColumnInfo(name = "syncedOn")
    var syncedOn: Date? = null,
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}