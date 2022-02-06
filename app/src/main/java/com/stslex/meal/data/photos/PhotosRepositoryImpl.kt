package com.stslex.meal.data.photos

import com.stslex.meal.data.source.PhotosPagingSource
import com.stslex.meal.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: PhotosPagingSource.Factory
) : PhotosRepository {

    override fun queryAll(): PhotosPagingSource = pagingSourceFactory.create()
}