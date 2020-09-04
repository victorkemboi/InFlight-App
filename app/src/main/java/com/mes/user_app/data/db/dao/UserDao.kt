package com.mes.user_app.data.db.dao



import androidx.lifecycle.LiveData
import androidx.room.*
import com.mes.user_app.data.db.model.UserDb

@Dao
interface UserDao {

    @Insert
    fun insertUser(userDb: UserDb): Long

    @Delete
    fun deleteUser(userDb: UserDb)

    @Update
    fun updateUser(userDb: UserDb): Int

    @Query("SELECT * FROM user WHERE id=:id   LIMIT  1")
    fun getUser(id:String): LiveData<UserDb>

    @Query("SELECT * FROM user  LIMIT  50")
    fun getUsers(): LiveData<List<UserDb>>

    @Query("SELECT COUNT(*) FROM user WHERE id=:id  ")
    fun checkUserExists(id:String): Int

    @Query("SELECT * FROM user WHERE Email=:email   LIMIT  1")
    fun getUserByEmail(email:String): UserDb

}