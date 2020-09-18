package com.mes.inflight_mag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data  class Issue (
    @SerializedName("issue_id")
    @ColumnInfo(name = "issue_id")
    var issueId: String = "",

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String = "",

    @SerializedName("magazine_id")
    @ColumnInfo(name = "magazine_id")
    var magazineId: String = "",

    @SerializedName("issue_period")
    @ColumnInfo(name = "issue_period")
    var issuePeriod: String = "",

    @SerializedName("frequent_flier_program_link")
    @ColumnInfo(name = "frequent_flier_program_link")
    var frequentFlierProgramLink: String = "",

    @SerializedName("cover_image_link")
    @ColumnInfo(name = "cover_image_link")
    var coverImageLink: String = "",

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