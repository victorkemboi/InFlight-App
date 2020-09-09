package com.mes.user_app.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mes.user_app.data.api.ApiService
import com.mes.user_app.utils.SharedPrefs
import com.mes.user_app.utils.livedata_adapter.LiveDataCallAdapterFactory
import com.mes.user_app.utils.net_adapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(sharedPrefs: SharedPrefs): OkHttpClient {
        val client = OkHttpClient.Builder()

        client.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization",String.format("Bearer %s", sharedPrefs.token)).build()
            chain.proceed(request)

        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client.addInterceptor(interceptor)
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideApiService(gson: Gson, okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://5e510330f2c0d300147c034c.mockapi.io/")
            .client(okHttpClient)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build().create(ApiService::class.java)
    }
}