package com.stslex.meal.di.modules

import com.stslex.meal.ui.navigation.NavigationHost
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        MapperModule::class,
        PagingModule::class,
        RetrofitModule::class,
        RepositoryModule::class,
        ServiceModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindsNavigationHost(navigationHost: NavigationHost.Base): NavigationHost
}