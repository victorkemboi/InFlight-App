package com.mes.inflight_mag.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.*
import com.mes.inflight_mag.data.repository.IssueRepo
import com.mes.inflight_mag.data.repository.MagazineRepo
import com.mes.inflight_mag.utils.UtilityClass
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MagazinesViewModel@ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val magazineRepo: MagazineRepo,
    private val issueRepo: IssueRepo

) : ViewModel()  {
    private  var magazines = MutableLiveData<ArrayList<Magazine>>()
    var loading = MutableLiveData(false)
    var airline :Airline? = null


    fun fetchMagazines(){
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            val airlineMagazines = magazineRepo.getAirlineMagazines(
                airline!!.airlineId)  as ArrayList<Magazine>
            when( airlineMagazines.size ){
                0 -> {}
                else->{
                    val magazinesCountUpdate = arrayListOf<Magazine>()
                    for (magazine in airlineMagazines){

                        magazine.issueCount = issueRepo.getMagazineIssueCount(magazine.magazineId)
                        magazinesCountUpdate.add(magazine)
                    }
                    GlobalScope.launch(Dispatchers.Main) {
                        loading.postValue(false)
                        magazines.postValue(magazinesCountUpdate)

                    }
                }
            }
        }
        }

    fun getMagazines(): LiveData<ArrayList<Magazine>>{
        return magazines
    }
}