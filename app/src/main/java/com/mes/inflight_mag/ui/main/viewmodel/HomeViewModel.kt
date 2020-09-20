package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.data.repository.AirlineRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val airlineRepo: AirlineRepo
) : ViewModel() {
    private  var airlines = MutableLiveData<ArrayList<Airline>>()
    var loading = MutableLiveData(false)

    init {
        fetchAirlinesFromNet()
    }

    private fun fetchAirlinesFromNet() {
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            when (val response = airlineRepo.fetchAirlines()) {
                is NetworkResponse.Success -> {
                    val airlineList = response.body as ArrayList<Airline>
                    when (airlineList.size) {
                        0 -> {
                        }
                        else -> {

                            GlobalScope.launch(Dispatchers.Main) {

                                loading.postValue(false)
                                airlines.postValue(airlineList)

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

    fun getAirlines(): LiveData<ArrayList<Airline>> {

        return airlines
    }
}