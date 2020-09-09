package com.mes.user_app.data.repository.data_src

import androidx.lifecycle.LiveData
import com.mes.user_app.data.api.ApiService
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.db.model.UserDb
import com.mes.user_app.data.model.UserM
import com.mes.user_app.data.model.core.User
import com.mes.user_app.data.repository.UserRepo
import com.mes.user_app.utils.livedata_adapter.ApiResponse

class UserDataSrc(
    private val userApiService: ApiService,
    private val userDao: UserDao) : UserRepo{

    override fun saveUser(user: User) {
        userDao.insertUser(user as UserDb)
    }

    override fun getUser(id: String): LiveData<User> {

       return userDao.getUser(id) as LiveData<User>
    }

    override fun checkUserExists(id: String): Boolean {
       return when( userDao.checkUserExists(id)){
           0 -> false
           else -> true
       }
    }

    override fun fetchUser(): LiveData<ApiResponse<List<User>>> {
        return userApiService.getUsers() as LiveData<ApiResponse<List<User>>>
    }

    override fun getUsers(): LiveData<List<User>> {
       val users= userDao.getUsers()
        return users as LiveData<List<User>>
    }

}