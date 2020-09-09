package com.mes.user_app.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.user_app.data.db.model.User
import com.mes.user_app.data.model.core.UserB
import com.mes.user_app.data.repository.UserRepo
import com.mes.user_app.utils.UtilityClass
import com.mes.user_app.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor (
    private val userRepository: UserRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private  var users = MutableLiveData<List<UserB>>()

    init {
        fetchUsersFromNet()
        fetchUsers()
    }
    private fun fetchUsers() =
        viewModelScope.launch (Dispatchers.IO){
            users.postValue(userRepository.getUsers())
        }


    private fun fetchUsersFromNet() {
        viewModelScope.launch (Dispatchers.IO){
            when (val response = userRepository.fetchUser()) {
                is NetworkResponse.Success -> {
                    val userList = response.body as ArrayList<User>
                    Log.d("Users: ", userList.toString())
                    when (userList.size) {
                        0 -> {
                        }
                        else -> {
                            var count = 0
                            for (user in userList){
                                    when{
                                        count < 25 -> {
                                            if(userRepository.checkUserExists(user.id)){
                                                user.addedOn = UtilityClass.getCurrentDateTime()
                                                user.syncStatus = "synced"
                                                user.syncedOn = UtilityClass.getCurrentDateTime()
                                                userRepository.saveUser(user)
                                                count += 1
                                            }
                                        }
                                        else -> break
                                    }

                            }

                        }
                    }
                }

                is NetworkResponse.NetworkError -> {
                    Log.d("Network Error: ", response.error.message.toString())
                }

            }

        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun getUsers(): LiveData<List<UserB>> {
        return users
    }

}