package com.mes.inflight_mag.data.db.dao

import androidx.room.*
import com.mes.inflight_mag.data.db.model.FavouriteIssue


@Dao
interface FavouriteIssueDao  {
    @Insert
    fun insert(favouriteIssue: FavouriteIssue): Long

    @Delete
    fun delete(favouriteIssue: FavouriteIssue)

    @Update
    fun update(favouriteIssue: FavouriteIssue): Int

    @Query("SELECT * FROM favourite_issue WHERE favourite_issue_id=:id   LIMIT  1")
    fun get(id:String): FavouriteIssue

    @Query("SELECT * FROM favourite_issue WHERE issue_id=:id   LIMIT  1")
    fun getByIssueId(id:String): FavouriteIssue

    @Query("SELECT * FROM favourite_issue ")
    fun getAll(): List<FavouriteIssue>

    @Query("SELECT COUNT() FROM favourite_issue WHERE favourite_issue_id=:id  ")
    fun checkExists(id:String): Int

    @Query("SELECT EXISTS (SELECT 1 FROM favourite_issue WHERE issue_id = :id) ")
    fun isFavourite(id:String): Boolean

}