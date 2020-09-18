package com.mes.inflight_mag.data.repository
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import java.lang.Error

interface UserRepo {

    suspend  fun saveUser(user: User)

    suspend fun getUser(id:String): User

    suspend fun checkUserExists(id: Int): Boolean

    suspend fun fetchUser(): NetworkResponse<List<User>, Error>

    suspend fun getUsers(): List<User>

}