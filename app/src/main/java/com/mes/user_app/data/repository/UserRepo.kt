package com.mes.user_app.data.repository

import com.mes.user_app.data.db.model.User
import com.mes.user_app.data.model.core.UserB
import com.mes.user_app.utils.net_adapter.NetworkResponse
import java.lang.Error

interface UserRepo {

    suspend  fun saveUser(user: User)

    suspend fun getUser(id:String): User

    suspend fun checkUserExists(id: String): Boolean

    suspend fun fetchUser(): NetworkResponse<List<User>, Error>

    suspend fun getUsers(): List<UserB>

}