package com.mes.user_app.data.api

import com.mes.user_app.data.db.model.User
import com.mes.user_app.utils.net_adapter.NetworkResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<User>, Error>
}