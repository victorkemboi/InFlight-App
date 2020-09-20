package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.Customer
import com.mes.inflight_mag.data.db.model.Login
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.data.repository.UserRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel @ViewModelInject constructor (
    private val userRepository: UserRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel()  {

    lateinit var username:String
    lateinit var password:String
    var signInSuccess = MutableLiveData(false)
    var loading = MutableLiveData(false)
    var customer: MutableLiveData<Customer?> = MutableLiveData<Customer?>()
    var message: MutableLiveData<String> = MutableLiveData("")


    fun signIn(){
        loading.postValue(true)
            GlobalScope.launch (Dispatchers.IO){
                val login = Login(
                    username,password
                )
                when (val response = userRepository.signIn(login)) {

                    is NetworkResponse.Success -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            Log.d("Net Resp",response.toString())
                            customer.postValue(response.body)
                            signInSuccess.postValue(true)
                            loading.postValue(false)

                            message.postValue("Successfully signed in!")
                        }



                    }

                    is NetworkResponse.NetworkError -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            Log.d("Net Resp", response.toString())
                            customer.postValue(null)
                            signInSuccess.postValue(false)
                            loading.postValue(false)
                            message.postValue("Kindly check your network to sign in.")
                        }
                    }

                    else -> {
                        GlobalScope.launch(Dispatchers.Main) {
                            Log.d("Net Resp", response.toString())
                            customer.postValue(null)
                            signInSuccess.postValue(false)
                            loading.postValue(false)
                            Log.d("Network Error: ",response.toString())
                            message.postValue("Sign in failed! Check your credentials.")
                        }
                    }
                }

        }

    }
}