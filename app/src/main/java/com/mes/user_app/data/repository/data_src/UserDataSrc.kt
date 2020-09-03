package com.mes.user_app.data.repository.data_src

import androidx.lifecycle.LiveData
import com.mes.user_app.data.api.ApiService
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.db.model.User
import com.mes.user_app.data.model.UserM
import com.mes.user_app.data.repository.UserRepo
import com.mes.user_app.utils.livedata_adapter.ApiResponse

class UserDataSrc(
    private val userApiService: ApiService,
    private val userDao: UserDao) : UserRepo{
    override fun saveUser(user: com.mes.user_app.data.db.model.User) {
        userDao.insertUser(user)
    }

    override fun getUser(id: String): LiveData<User> {

        return userDao.getUser(id)
    }

    override fun checkUserExists(id: String): Boolean {
       return when( userDao.checkUserExists(id)){
           0 -> false
           else -> true
       }
    }

    override fun fetchUser(id: String): LiveData<ApiResponse<List<UserM>>> {
        return userApiService.getUsers()
    }

    override fun getUsers(): LiveData<List<User>> {
        return userDao.getUsers()
    }

}