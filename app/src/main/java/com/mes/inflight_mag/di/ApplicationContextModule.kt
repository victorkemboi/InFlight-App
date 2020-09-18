package com.mes.inflight_mag.di

import android.content.Context
import com.mes.inflight_mag.utils.SharedPrefs
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