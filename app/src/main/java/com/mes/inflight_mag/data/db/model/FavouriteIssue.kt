package com.mes.inflight_mag.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mes.inflight_mag.utils.DateTypeConverter
import com.mes.inflight_mag.utils.UtilityClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "favourite_issue")
@TypeConverters(DateTypeConverter::class)
data class FavouriteIssue (
    @SerializedName("favourite_issue_id")
    @ColumnInfo(name = "favourite_issue_id")
    var favouriteIssueId: String = "",

    @SerializedName("issue_id")
    @ColumnInfo(name = "issue_id")
    var issueId: String = "",

    @SerializedName("user_id")
    @ColumnInfo(name = "user_id")
    var userId: String = "",

    @SerializedName("liked")
    @ColumnInfo(name = "liked")
    var liked: Boolean = true,

    @ColumnInfo(name = "createdLocally")
    var createdLocally: Boolean = false,

    @ColumnInfo(name = "syncStatus")
    var syncStatus: String = "None",

    @ColumnInfo(name = "syncedOn")
    var syncedOn: Date? = null,

    @ColumnInfo(name = "addedOn")
    var addedOn: Date = UtilityClass.getCurrentDateTime(),
):
    Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}