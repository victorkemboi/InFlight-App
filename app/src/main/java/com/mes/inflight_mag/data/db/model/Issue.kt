package com.mes.inflight_mag.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mes.inflight_mag.utils.DateTypeConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "issue")
@TypeConverters(DateTypeConverter::class)
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
):
    Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}