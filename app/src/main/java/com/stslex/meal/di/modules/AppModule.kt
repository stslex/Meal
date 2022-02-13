package com.stslex.meal.di.modules

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
interface AppModule