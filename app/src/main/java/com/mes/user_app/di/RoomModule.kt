package com.mes.user_app.di



import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mes.user_app.App
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.db.model.MvvmDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule
{


    @Singleton
    @Provides
    internal fun providesRoomDatabase(@ApplicationContext applicationContext: Context): MvvmDB {

        return  MvvmDB.getInstance(applicationContext)

    }


    @Provides
    @Singleton
    internal fun provideUserDao(db: MvvmDB): UserDao {
        return db.userDao()
    }

}