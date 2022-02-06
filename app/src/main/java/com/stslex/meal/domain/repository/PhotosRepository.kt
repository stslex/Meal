package com.stslex.meal.domain.repository

import com.stslex.meal.data.source.PhotosPagingSource

interface PhotosRepository {
    fun queryAll(): PhotosPagingSource
}