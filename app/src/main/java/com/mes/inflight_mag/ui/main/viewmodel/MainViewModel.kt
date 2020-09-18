package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.data.repository.UserRepo
import com.mes.inflight_mag.utils.UtilityClass
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor (
    private val userRepository: UserRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private  var users = MutableLiveData<ArrayList<User>>()

    init {
        fetchUsersFromNet()
        fetchUsers()
    }

    private fun fetchUsers() =
        viewModelScope.launch (Dispatchers.IO){
            val dbUsers = userRepository.getUsers() as ArrayList<User>
            users.postValue(dbUsers)
            Log.d("Fetched Users: ", users.value.toString())
        }


    private fun fetchUsersFromNet() {
        viewModelScope.launch (Dispatchers.IO){
            when (val response = userRepository.fetchUser()) {
                is NetworkResponse.Success -> {
                    val userList = response.body as ArrayList<User>
                    when (userList.size) {
                        0 -> {
                        }
                        else -> {
                            var count = 0
                            for (user in userList){
                                    when{
                                        count < 25 -> {
                                            if(!userRepository.checkUserExists(user.id)){
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
                            fetchUsers()
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

    fun getUsers(): LiveData<ArrayList<User>> {

        return users
    }


}