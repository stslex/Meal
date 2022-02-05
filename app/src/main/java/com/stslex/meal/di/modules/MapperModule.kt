package com.stslex.meal.di.modules

import androidx.paging.PagingData
import com.stslex.core.Mapper
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.domain.mapper.PagingImageMapper
import com.stslex.meal.ui.model.ImageModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.Flow

@Module
@InstallIn(ActivityComponent::class)
class MapperModule {

    @Provides
    fun providesPagingPhotosMapper(): Mapper.Data<PagingData<ImageEntity>, Flow<PagingData<ImageModel>>> =
        PagingImageMapper()
}