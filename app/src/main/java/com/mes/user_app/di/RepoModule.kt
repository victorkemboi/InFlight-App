package com.mes.user_app.di

import com.mes.user_app.data.api.ApiService
import com.mes.user_app.data.db.dao.UserDao
import com.mes.user_app.data.repository.UserRepo
import com.mes.user_app.data.repository.data_src.UserDataSrc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {

    @Singleton
    @Provides
    internal fun providesUserRepo(api: ApiService, userDao: UserDao): UserRepo =
        UserDataSrc(api,userDao)
}