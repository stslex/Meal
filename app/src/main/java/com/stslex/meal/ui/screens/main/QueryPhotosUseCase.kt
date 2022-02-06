package com.stslex.meal.ui.screens.main

import com.stslex.meal.data.source.PhotosPagingSource
import com.stslex.meal.domain.repository.PhotosRepository
import javax.inject.Inject

class QueryPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository
) {

    operator fun invoke(): PhotosPagingSource = repository.queryAll()
}