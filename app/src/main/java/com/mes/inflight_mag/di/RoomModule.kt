package com.mes.inflight_mag.di



import android.content.Context
import com.mes.inflight_mag.data.db.dao.UserDao
import com.mes.inflight_mag.data.db.model.MvvmDB
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

        return  MvvmDB.getInstance(applicationContext)

    }


    @Provides
    @Singleton
    internal fun provideUserDao(db: MvvmDB): UserDao {
        return db.userDao()
    }

}