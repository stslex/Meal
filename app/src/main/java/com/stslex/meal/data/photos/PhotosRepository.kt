package com.stslex.meal.data.photos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.stslex.meal.data.model.RemoteImageModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PhotosRepository {

    @ExperimentalCoroutinesApi
    fun listenAllPhotos(): Flow<PagingData<RemoteImageModel>>

    class Base @Inject constructor(
        private val pagingSource: PhotosPagingSource,
        private val pagingConfig: PagingConfig
    ) : PhotosRepository {

        @ExperimentalCoroutinesApi
        override fun listenAllPhotos(): Flow<PagingData<RemoteImageModel>> = Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory
        ).flow

        private val pagingSourceFactory: () -> PagingSource<Int, RemoteImageModel>
            get() = { pagingSource.apply { invalidate() } }
    }
}