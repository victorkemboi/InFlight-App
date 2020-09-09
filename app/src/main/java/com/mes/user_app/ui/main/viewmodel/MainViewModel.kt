package com.mes.user_app.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.user_app.data.model.core.User
import com.mes.user_app.data.repository.UserRepo
import com.mes.user_app.utils.livedata_adapter.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor (
    private val userRepository: UserRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var users : LiveData<List<User>>

    init {
        fetchUsers()
    }

    private fun fetchUsers() =
            liveData {
                emitSource(userRepository.getUsers())
            }


    fun userList(): LiveData<ApiResponse<List<User>>>
    = liveData{
        Log.d("Observer: ", "on Livedata")
        //viewModelScope.launch {
            Log.d("Observer: ", "On wait emit")
       emitSource(userRepository.fetchUser())
    }
    suspend fun usersResp(): LiveData<ApiResponse<List<User>>> =
        withContext(Dispatchers.IO){
        userRepository.fetchUser()
    }



    override fun onCleared() {
        super.onCleared()
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

}