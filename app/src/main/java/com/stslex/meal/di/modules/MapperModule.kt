package com.stslex.meal.di.modules

import com.stslex.core.Mapper
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.domain.mapper.PagingImageMapper
import com.stslex.meal.ui.model.ImageModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MapperModule {

    @Provides
    fun providesPagingPhotosMapper(): Mapper.Data<ImageEntity, ImageModel> =
        PagingImageMapper()
}