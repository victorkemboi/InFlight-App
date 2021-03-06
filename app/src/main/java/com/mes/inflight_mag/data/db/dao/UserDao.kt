package com.mes.inflight_mag.data.db.dao



import androidx.room.*
import com.mes.inflight_mag.data.db.model.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User): Int

    @Query("SELECT * FROM user WHERE id=:id   LIMIT  1")
    fun getUser(id:String): User

    @Query("SELECT * FROM user  LIMIT  50")
    fun getUsers(): List<User>

    @Query("SELECT COUNT() FROM user WHERE id=:id  ")
    fun checkUserExists(id:Int): Int

    @Query("SELECT * FROM user WHERE Email=:email   LIMIT  1")
    fun getUserByEmail(email:String): User
}