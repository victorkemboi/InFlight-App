package com.mes.user_app.data.repository

import androidx.lifecycle.LiveData
import com.mes.user_app.data.db.model.User
import com.mes.user_app.data.model.UserM
import com.mes.user_app.utils.livedata_adapter.ApiResponse

interface UserRepo {

    fun saveUser(user: User)

    fun getUser(id:String): LiveData<User>

    fun checkUserExists(id: String): Boolean

    fun fetchUser(id:String): LiveData<ApiResponse<List<UserM>>>

    fun getUsers(): LiveData<List<User>>

}