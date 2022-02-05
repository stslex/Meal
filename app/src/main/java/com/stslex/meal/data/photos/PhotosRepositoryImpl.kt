package com.stslex.meal.data.photos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val pagingSource: PagingSource<Int, ImageEntity>,
    private val pagingConfig: PagingConfig
) : PhotosRepository {

    override fun getAllPhotos(): Flow<PagingData<ImageEntity>> = Pager(
        config = pagingConfig,
        pagingSourceFactory = pagingSourceFactory
    ).flow

    private val pagingSourceFactory: () -> PagingSource<Int, ImageEntity>
        get() = { pagingSource.apply { invalidate() } }
}