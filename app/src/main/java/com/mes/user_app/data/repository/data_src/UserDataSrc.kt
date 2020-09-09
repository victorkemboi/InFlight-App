package com.mes.user_app.data.repository.data_src

import com.mes.user_app.data.api.ApiService
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.db.model.User
import com.mes.user_app.data.repository.UserRepo
import com.mes.user_app.utils.net_adapter.NetworkResponse
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

    override suspend fun checkUserExists(id: String): Boolean {
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