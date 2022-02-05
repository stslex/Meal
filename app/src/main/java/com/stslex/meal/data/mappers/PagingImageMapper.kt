package com.stslex.meal.data.mappers

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex.meal.data.model.RemoteImageModel
import com.stslex.meal.ui.model.ImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

fun interface PagingImageMapper {

    fun map(data: PagingData<RemoteImageModel>): Flow<PagingData<ImageModel>>

    class Base @Inject constructor() : PagingImageMapper {

        override fun map(data: PagingData<RemoteImageModel>): Flow<PagingData<ImageModel>> = flowOf(
            data.map {
                ImageModel.Base(id = it.id, url = it.urls.regular)
            }
        )
    }
}