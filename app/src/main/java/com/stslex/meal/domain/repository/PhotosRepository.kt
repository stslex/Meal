package com.stslex.meal.domain.repository

import androidx.paging.PagingData
import com.stslex.meal.data.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getAllPhotos(): Flow<PagingData<ImageEntity>>
}