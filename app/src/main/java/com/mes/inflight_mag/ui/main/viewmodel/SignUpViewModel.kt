package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mes.inflight_mag.data.db.model.Customer
import com.mes.inflight_mag.data.db.model.Registration
import com.mes.inflight_mag.data.repository.UserRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel @ViewModelInject constructor (
    private val userRepository: UserRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    lateinit var username:String
    lateinit var email:String
    lateinit var phoneNumber: Number
    lateinit var password:String

    var loading = MutableLiveData(false)
    var customer: MutableLiveData<Customer?> = MutableLiveData<Customer?>()
    var message: MutableLiveData<String> = MutableLiveData("")


    fun signUp(){
        loading.postValue(true)
        GlobalScope.launch (Dispatchers.IO){
            val registration = Registration(
                username,
                email,
                password,
                "",
                "",
                phoneNumber
            )
            when (val response = userRepository.register(registration)) {
                is NetworkResponse.Success -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        Log.d("Net Resp",response.toString())
                        customer.postValue(response.body)
                        loading.postValue(false)

                        message.postValue("Successfully registered!")
                    }
                }
                is NetworkResponse.NetworkError -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        Log.d("Net Resp", response.toString())
                        customer.postValue(null)
                        loading.postValue(false)
                        message.postValue("Kindly check your network to register.")
                    }
                }
                else -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        Log.d("Net Resp", response.toString())
                        customer.postValue(null)
                        loading.postValue(false)
                        Log.d("Network Error: ",response.toString())
                        message.postValue("Registration failed! Try again.")
                    }
                }
            }
        }
    }
}

