package com.mes.inflight_mag.data.repository

import com.mes.inflight_mag.data.db.model.FavouriteIssue

interface FavouritesRepo {
    suspend fun saveFavouriteIssue(favouriteIssue: FavouriteIssue)
    suspend fun checkExists(fav_id:String): Boolean
    suspend fun isFavourite(issue_id:String): Boolean
    suspend fun getByIssue(issue_id: String): FavouriteIssue
}