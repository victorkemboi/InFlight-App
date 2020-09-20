package com.mes.inflight_mag.data.db.model

data class Registration(
    val name: String,
    val email: String,
    val password: String,
    var device_id: String = "",
    var device_type: String = "",
    val mobile_number: Number
)