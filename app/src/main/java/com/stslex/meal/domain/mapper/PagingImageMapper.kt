package com.stslex.meal.domain.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.core.Mapper
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.ui.model.ImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PagingImageMapper @Inject constructor() :
    Mapper.Data<PagingData<ImageEntity>, Flow<PagingData<ImageModel>>> {

    override fun map(data: PagingData<ImageEntity>): Flow<PagingData<ImageModel>> = flowOf(
        data.map {
            ImageModel.Base(id = it.id, url = it.urls.regular)
        }
    )
}