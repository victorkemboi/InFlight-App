package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.data.db.model.MagazineRequest
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.data.repository.MagazineRepo
import com.mes.inflight_mag.utils.UtilityClass
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MagazinesViewModel@ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val magazineRepo: MagazineRepo,

) : ViewModel()  {
    private  var magazines = MutableLiveData<ArrayList<Magazine>>()
    var loading = MutableLiveData(false)
    var airline :Airline? = null
    init {

        fetchMagazinesFromNet()
    }

    private fun fetchMagazines(){
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            val airlineMagazines = magazineRepo.getAirlineMagazines(
                airline!!.airlineId)  as ArrayList<Magazine>
            when( airlineMagazines.size ){
                0 -> {}
                else->{
                    GlobalScope.launch(Dispatchers.Main) {
                        loading.postValue(false)
                        magazines.postValue(airlineMagazines)

                    }
                }
            }
        }
        }

    private fun fetchMagazinesFromNet() {
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            when (val response = magazineRepo.fetchMagazines(
                MagazineRequest(
                    "airline",
                    airline!!.airlineId
                )
            )) {
                is NetworkResponse.Success -> {
                    val magazineList = response.body as ArrayList<Magazine>
                    Log.d("Network Error: ", magazineList.toString())
                    when (magazineList.size) {
                        0 -> {
                        }
                        else -> {

                            for(magazine in magazineList){
                                if(!magazineRepo.checkMagazineExists(magazine.magazineId)){
                                    magazine.addedOn = UtilityClass.getCurrentDateTime()
                                    magazine.syncStatus = "synced"
                                    magazine.syncedOn = UtilityClass.getCurrentDateTime()
                                    magazineRepo.saveMagazine(magazine)
                                }
                            }
                            GlobalScope.launch(Dispatchers.Main) {
                                loading.postValue(false)
                                fetchMagazines()
                            }

                        }
                    }
                }

                is NetworkResponse.NetworkError -> {
                    Log.d("Network Error: ", response.error.message.toString())
                }
                else ->{
                    GlobalScope.launch(Dispatchers.Main) {
                        loading.postValue(false)
                        Log.d("Network Error: ", response.toString())
                    }
                }

            }

        }
    }

    fun getNoOfIssues(magazineId: String){

    }

    fun getMagazines(): LiveData<ArrayList<Magazine>>{
        return magazines
    }
}