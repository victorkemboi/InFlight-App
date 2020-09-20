package com.mes.inflight_mag.data.db.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mes.inflight_mag.data.db.dao.AirlineDao
import com.mes.inflight_mag.data.db.dao.IssueDao
import com.mes.inflight_mag.data.db.dao.MagazineDao
import com.mes.inflight_mag.data.db.dao.UserDao

@Database(entities = [ User::class, Airline::class, Customer::class, Magazine::class], version = 1, exportSchema = false)
abstract class MvvmDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun airlineDao(): AirlineDao

    abstract fun magazineDao(): MagazineDao

    abstract fun issueDao(): IssueDao

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