package com.stslex.meal.di.modules

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.stslex.meal.data.api.PhotosApiService
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.data.source.PhotosPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class PagingModule {

    @Provides
    fun providesDefaultPagingConfig(): PagingConfig = PagingConfig(
        pageSize = DEFAULT_PAGE_SIZE,
        enablePlaceholders = PLACE_HOLDER_ENABLE
    )

    @Provides
    fun providesPagingPhotosSource(service: PhotosApiService): PagingSource<Int, ImageEntity> =
        PhotosPagingSource(service)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
        private const val PLACE_HOLDER_ENABLE = false
    }
}