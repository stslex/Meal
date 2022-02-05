package com.stslex.meal.di.modules

import com.stslex.meal.data.api.PhotosApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class ServiceModule {

    @Provides
    fun providesPhotosService(retrofit: Retrofit): PhotosApiService =
        retrofit.create(PhotosApiService::class.java)
}