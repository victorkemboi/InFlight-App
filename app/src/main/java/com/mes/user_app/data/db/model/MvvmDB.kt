package com.mes.user_app.data.db.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mes.user_app.data.db.dao.UserDao
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(entities = [ UserDb::class], version = 1, exportSchema = false)
abstract class MvvmDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private  var INSTANCE : MvvmDB? = null
        fun getInstance(applicationContext: Context): MvvmDB{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        applicationContext.applicationContext, MvvmDB::class.java,
                        "mvvm.db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return  instance
            }

        }
    }
}