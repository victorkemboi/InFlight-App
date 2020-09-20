package com.mes.inflight_mag.data.db.dao

import androidx.room.*
import com.mes.inflight_mag.data.db.model.Issue

@Dao
interface IssueDao {
    @Insert
    fun insert(issue: Issue): Long

    @Delete
    fun delete(issue: Issue)

    @Update
    fun update(issue: Issue): Int

    @Query("SELECT * FROM issue WHERE issue_id=:id   LIMIT  1")
    fun get(id:String): Issue

    @Query("SELECT * FROM issue ")
    fun getAll(): List<Issue>

    @Query("SELECT COUNT() FROM issue WHERE issue_id=:id  ")
    fun checkExists(id:String): Int

    @Query("SELECT COUNT() FROM issue WHERE magazine_id=:id   ")
    fun getIssueCount(id:String): Int
}