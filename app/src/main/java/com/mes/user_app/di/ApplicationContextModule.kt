package com.mes.user_app.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(
    ApplicationComponent::class)
class ApplicationContextModule (private var applicationContext: Context){

    @Provides
    @ApplicationContext
    fun provideContext() : Context {
        return applicationContext;
    }

    @Provides
     fun provideApplication(): Application {
        return applicationContext.applicationContext as Application
    }

}