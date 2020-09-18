package com.mes.inflight_mag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mes.inflight_mag.utils.UtilityClass
import java.util.*

data class Magazine (
    @SerializedName("magazine_id")
    @ColumnInfo(name = "magazine_id")
    var userId: String = "",

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title : String = "",

    @SerializedName("publisher")
    @ColumnInfo(name = "publisher")
    var publisher: String = "",

    @SerializedName("geographic_region")
    @ColumnInfo(name = "geographic_region")
    var geographicRegion: String = "",

    @SerializedName("sub_category_id")
    @ColumnInfo(name = "sub_category_id")
    var subCategoryId: String = "",

    @SerializedName("airline_id")
    @ColumnInfo(name = "airline_id")
    var airlineId: String = "",

    @SerializedName("icon")
    @ColumnInfo(name = "icon")
    var icon: String = "",

    @ColumnInfo(name = "addedOn")
    var addedOn: Date = UtilityClass.getCurrentDateTime(),

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