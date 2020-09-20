package com.mes.inflight_mag.di

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.AirlineDao
import com.mes.inflight_mag.data.db.dao.MagazineDao
import com.mes.inflight_mag.data.db.dao.UserDao
import com.mes.inflight_mag.data.repository.AirlineRepo
import com.mes.inflight_mag.data.repository.MagazineRepo
import com.mes.inflight_mag.data.repository.UserRepo
import com.mes.inflight_mag.data.repository.data_src.AirlineDataSrc
import com.mes.inflight_mag.data.repository.data_src.MagazineDataSrc
import com.mes.inflight_mag.data.repository.data_src.UserDataSrc
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
    @Singleton
    @Provides
    internal fun providesAirlineRepo(api: ApiService, airlineDao: AirlineDao): AirlineRepo =
        AirlineDataSrc(api,airlineDao)

    @Singleton
    @Provides
    internal fun providesMagazineRepo(api: ApiService, magazineDao: MagazineDao): MagazineRepo =
        MagazineDataSrc(api,magazineDao)

}