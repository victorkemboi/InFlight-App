package com.mes.user_app.data.api

import androidx.lifecycle.LiveData
import com.mes.user_app.data.model.UserM
import com.mes.user_app.utils.livedata_adapter.ApiResponse

interface ApiService {

    fun getUsers(): LiveData<ApiResponse<List<UserM>>>
}