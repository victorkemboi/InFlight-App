package com.mes.inflight_mag.data.api

import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<User>, Error>
}