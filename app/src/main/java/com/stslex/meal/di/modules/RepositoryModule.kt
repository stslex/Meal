package com.stslex.meal.di.modules

import com.stslex.meal.data.photos.PhotosRepositoryImpl
import com.stslex.meal.domain.repository.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsPhotosRepository(repository: PhotosRepositoryImpl): PhotosRepository
}