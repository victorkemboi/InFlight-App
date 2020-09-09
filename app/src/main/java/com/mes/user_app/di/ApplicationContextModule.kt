package com.mes.user_app.di

import android.app.Application
import android.content.Context
import com.mes.user_app.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(
    ApplicationComponent::class)
object ApplicationContextModule{

    @Provides
    @Singleton
    internal fun providesSharedPreferences(@ApplicationContext applicationContext: Context): SharedPrefs {
        return SharedPrefs(applicationContext)
    }


}