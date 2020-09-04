package com.mes.user_app.di



import android.content.Context
import androidx.room.Room
import com.mes.user_app.App
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.db.model.MvvmDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule
{


    @Singleton
    @Provides
    internal fun providesRoomDatabase(@ApplicationContext applicationContext: Context): MvvmDB {
        return Room.databaseBuilder(applicationContext, MvvmDB::class.java, "my_db")
            .build()
    }


    @Provides
    @Singleton
    internal fun provideUserDao(@ApplicationContext applicationContext: Context): UserDao {
        return  MvvmDB.getInstance(applicationContext)
    }


}