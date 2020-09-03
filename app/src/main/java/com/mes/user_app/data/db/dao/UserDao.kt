package com.mes.user_app.data.db.dao



import androidx.lifecycle.LiveData
import androidx.room.*
import com.mes.user_app.data.db.model.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User): Int

    @Query("SELECT * FROM user WHERE id=:id   LIMIT  1")
    fun getUser(id:String): LiveData<User>

    @Query("SELECT * FROM user  LIMIT  50")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT COUNT(*) FROM user WHERE id=:id  ")
    fun checkUserExists(id:String): Int

    @Query("SELECT * FROM user WHERE Email=:email   LIMIT  1")
    fun getUserByEmail(email:String): User

}