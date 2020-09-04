package com.mes.user_app.data.repository

import androidx.lifecycle.LiveData
import com.mes.user_app.data.model.core.User
import com.mes.user_app.utils.livedata_adapter.ApiResponse

interface UserRepo {

    fun saveUser(user: User)

    fun getUser(id:String): LiveData<User>

    fun checkUserExists(id: String): Boolean

    fun fetchUser(id:String): LiveData<ApiResponse<List<User>>>

    fun getUsers(): LiveData<List<User>>

}