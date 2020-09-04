package com.mes.user_app.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.user_app.data.model.core.User
import com.mes.user_app.data.repository.UserRepo
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor (
    private val userRepository: UserRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val users = MutableLiveData<List<User>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            userRepository.getUsers()
        }

    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

}