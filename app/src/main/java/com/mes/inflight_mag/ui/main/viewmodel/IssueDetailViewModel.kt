package com.mes.inflight_mag.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mes.inflight_mag.data.db.model.FavouriteIssue
import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.data.repository.FavouritesRepo
import com.mes.inflight_mag.utils.SharedPrefs
import com.mes.inflight_mag.utils.UtilityClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IssueDetailViewModel@ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val favouritesRepo: FavouritesRepo,
    private val sharedPrefs: SharedPrefs
): ViewModel() {
    var issue : Issue? = null
    var magazine : Magazine? = null

    private var favourite = MutableLiveData<FavouriteIssue?>(null)
    var isLiked = false

     fun checkIfFavourite(){
        viewModelScope.launch (Dispatchers.IO){
            if (issue!=null){
                val isIssueFavourite = favouritesRepo.isFavourite(issue!!.issueId)
                if (isIssueFavourite){
                    getFavouriteSaved()
                }else{
                    GlobalScope.launch(Dispatchers.Main) {
                        favourite.postValue(null)
                        isLiked = false
                    }
                }
            }

        }
    }

    private fun getFavouriteSaved(){
        viewModelScope.launch (Dispatchers.IO){
            if (issue != null){
                val favRecord = favouritesRepo.getByIssue(issue?.issueId!!)
                GlobalScope.launch(Dispatchers.Main) {
                    favourite.postValue(favRecord)
                    isLiked = true
                }
            }

        }
    }

    fun addFavourite(){
        viewModelScope.launch (Dispatchers.IO){
            val record = FavouriteIssue(
                "",issue?.issueId!!,
                sharedPrefs.user!!, liked = true, createdLocally = true, syncStatus = "None",
                syncedOn = null, addedOn = UtilityClass.getCurrentDateTime()
            )
            favouritesRepo.saveFavouriteIssue(record)
            checkIfFavourite()
        }
    }

    fun getFavourite():LiveData<FavouriteIssue?>{
        return  favourite
    }


}