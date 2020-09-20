package com.mes.inflight_mag.data.api

import com.mes.inflight_mag.data.db.model.Customer
import com.mes.inflight_mag.data.db.model.Login
import com.mes.inflight_mag.data.db.model.Registration
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<User>, Error>

    @POST("register")
    suspend fun register(@Body customerInfo: Registration): NetworkResponse<Customer, Error>

    @POST("login")
    suspend fun login(@Body login: Login): NetworkResponse<Customer, Error>


}