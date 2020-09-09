package com.mes.user_app.data.api

import androidx.lifecycle.LiveData
import com.mes.user_app.data.model.UserM
import com.mes.user_app.data.model.core.User
import com.mes.user_app.utils.livedata_adapter.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): LiveData<ApiResponse<List<UserM>>>
}