package com.stslex.meal.di

import com.stslex.meal.ui.navigation.NavigationHost
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface AppModule {

    @Binds
    fun bindsNavigationHost(navigationHost: NavigationHost.Base): NavigationHost
}