package com.mes.inflight_mag.data.repository.data_src

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.UserDao
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.data.repository.UserRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import java.lang.Error

class UserDataSrc(
    private val userApiService: ApiService,
    private val userDao: UserDao) : UserRepo{

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUser(id: String): User {
       return userDao.getUser(id)
    }

    override suspend fun checkUserExists(id: Int): Boolean {
       return when( userDao.checkUserExists(id)){
           0 -> false
           else -> true
       }
    }

    override suspend fun fetchUser(): NetworkResponse<List<User>, Error> {
        return userApiService.getUsers()
    }

    override suspend fun getUsers(): List<User> {
        return userDao.getUsers()
    }

}