package com.mes.user_app.di



import androidx.room.Room
import com.mes.user_app.App
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.db.model.MvvmDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule
{


    @Singleton
    @Provides
    internal fun providesRoomDatabase(app: App): MvvmDB {
        return Room.databaseBuilder(app, MvvmDB::class.java, "my_db")
            .build()
    }


    @Provides
    @Singleton
    internal fun provideUserDao(db: MvvmDB): UserDao {
        return  db.userDao()
    }


}