package com.mes.inflight_mag.di

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.*
import com.mes.inflight_mag.data.repository.*
import com.mes.inflight_mag.data.repository.data_src.*
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

    @Singleton
    @Provides
    internal fun providesIssueRepo(api: ApiService, issueDao: IssueDao): IssueRepo =
        IssueDataSrc(api,issueDao)

    @Singleton
    @Provides
    internal fun providesFavouritesRepo(api: ApiService, favouriteIssueDao: FavouriteIssueDao):
            FavouritesRepo =   FavouritesDataSrc(api,favouriteIssueDao)

}