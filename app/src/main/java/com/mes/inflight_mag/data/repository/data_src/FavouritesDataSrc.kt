package com.mes.inflight_mag.data.repository.data_src

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.FavouriteIssueDao
import com.mes.inflight_mag.data.db.model.FavouriteIssue
import com.mes.inflight_mag.data.repository.FavouritesRepo

class FavouritesDataSrc(
    private val apiService: ApiService,
    private val favouriteIssueDao: FavouriteIssueDao
): FavouritesRepo {
    override suspend fun saveFavouriteIssue(favouriteIssue: FavouriteIssue) {
      favouriteIssueDao.insert(favouriteIssue)
    }

    override suspend fun checkExists(fav_id: String): Boolean {

        return when( favouriteIssueDao.checkExists(fav_id) ){
            0 -> false
            else -> true
        }
    }

    override suspend fun isFavourite(issue_id: String): Boolean {
        return  favouriteIssueDao.isFavourite(issue_id)
    }

    override suspend fun getByIssue(issue_id: String): FavouriteIssue {
       return favouriteIssueDao.getByIssueId(issue_id)
    }
}