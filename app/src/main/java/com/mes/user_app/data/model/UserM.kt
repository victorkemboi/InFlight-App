package com.mes.user_app.data.model

import com.google.gson.annotations.SerializedName
import com.mes.user_app.data.model.core.UserB

data class UserM(
        @SerializedName("id")
        override var id: String = "",

        @SerializedName("name")
        override var name: String = "",

        @SerializedName("email")
        override var email: String = "",

        @SerializedName("avatar")
        override var avatar: String = "",


        @SerializedName("createdLocally")
        override var createdLocally: Boolean = false,

        @SerializedName("syncStatus")
        override var syncStatus: String = ""

    ): UserB
