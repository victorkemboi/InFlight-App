package com.mes.user_app.data.db.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mes.user_app.data.db.dao.UserDao

@Database(entities = [ User::class], version = 1, exportSchema = false)
abstract class MvvmDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}