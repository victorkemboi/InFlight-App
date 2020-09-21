package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.data.db.model.MagazineRequest
import com.mes.inflight_mag.data.repository.AirlineRepo
import com.mes.inflight_mag.data.repository.IssueRepo
import com.mes.inflight_mag.data.repository.MagazineRepo
import com.mes.inflight_mag.utils.UtilityClass
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val airlineRepo: AirlineRepo,
    private val magazineRepo: MagazineRepo,
    private val issueRepo: IssueRepo
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
                                for(airline in airlineList){
                                    saveAirline(airline)
                                    fetchMagazinesFromNet(airline)
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
    private fun saveAirline(airline: Airline){
        viewModelScope.launch (Dispatchers.IO){

            if(!airlineRepo.checkExists(airline.airlineId)){
                airlineRepo.save(airline)
            }

        }
    }

    fun getAirlineList(){
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            val airlineList = airlineRepo.getAirlines()  as ArrayList<Airline>
            when( airlineList.size ){
                0 -> {}
                else->{
                    val airlineCountUpdate = arrayListOf<Airline>()
                    for (airline in airlineList){

                        airline.magCount = magazineRepo.getAirlineMagCount(airline.airlineId)
                        airlineCountUpdate.add(airline)
                    }
                    GlobalScope.launch(Dispatchers.Main) {
                        loading.postValue(false)
                        airlines.postValue(airlineCountUpdate)

                    }
                }
            }
        }
    }
    fun getAirlines(): LiveData<ArrayList<Airline>> {
        return airlines
    }

    private fun fetchMagazinesFromNet(airline: Airline) {
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            when (val response = magazineRepo.fetchMagazines(
                MagazineRequest(
                    "airline",
                    airline.airlineId
                )
            )) {
                is NetworkResponse.Success -> {
                    val magazineList = response.body as ArrayList<Magazine>
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
                                getIssues()
                                getAirlineList()
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

    private fun getIssues(){
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            when (val response = issueRepo.fetchIssues(
            )) {
                is NetworkResponse.Success -> {
                    val issueList = response.body as ArrayList<Issue>
                    when (issueList.size) {
                        0 -> {
                        }
                        else -> {

                            for(issue in issueList){
                                if(!issueRepo.checkIssueExists(issue.issueId)){
                                    issue.addedOn = UtilityClass.getCurrentDateTime()
                                    issue.syncStatus = "synced"
                                    issue.syncedOn = UtilityClass.getCurrentDateTime()
                                    issueRepo.saveIssue(issue)
                                }
                            }
                            GlobalScope.launch(Dispatchers.Main) {
                                loading.postValue(false)
                                getAirlines()
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
                    }
                }

            }
        }}


}