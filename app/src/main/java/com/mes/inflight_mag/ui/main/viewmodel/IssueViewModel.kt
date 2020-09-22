package com.mes.inflight_mag.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.Airline
import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.data.repository.IssueRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class IssueViewModel@ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val issueRepo: IssueRepo,
    ) : ViewModel()  {
    private  var issues = MutableLiveData<ArrayList<Issue>>()
    var loading = MutableLiveData(false)
    var magazine : Magazine? = null


    fun fetchIssues(){
        loading.postValue(true)
        viewModelScope.launch (Dispatchers.IO){
            val issueList = issueRepo.getMagazineIssues(
               magazine!!.magazineId)  as ArrayList<Issue>
            when( issueList.size ){
                0 -> {}
                else->{

                    GlobalScope.launch(Dispatchers.Main) {
                        loading.postValue(false)
                        issues.postValue(issueList)

                    }
                }
            }
        }
    }

    fun getIssues(): LiveData<ArrayList<Issue>> {
        return issues
    }
}