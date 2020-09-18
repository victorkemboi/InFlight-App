package com.mes.inflight_mag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

class Airline (

    @SerializedName("airline_id")
    @ColumnInfo(name = "airline_id")
    var airlineId: String = "",

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = "",

    @SerializedName("website_link")
    @ColumnInfo(name = "website_link")
    var websiteLink: String = "",

    @SerializedName("reservation_page_link")
    @ColumnInfo(name = "reservation_page_link")
    var reservationPageLink: String = "",

    @SerializedName("frequent_flier_program_link")
    @ColumnInfo(name = "frequent_flier_program_link")
    var frequentFlierProgramLink: String = "",

    @SerializedName("alliance_id")
    @ColumnInfo(name = "alliance_id")
    var allianceId: String = "",

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