package com.stslex.meal.ui.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.stslex.meal.ui.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryNewsUseCase>,
    private val pagingConfig: PagingConfig
) : ViewModel() {

    private val newPagerPhotos: Pager<Int, ImageModel> by lazy {
        Pager(pagingConfig) {
            newPagingPhotosSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase().also { newPagingPhotosSource = it }
        }
    }

    val photos: StateFlow<PagingData<ImageModel>> = newPagerPhotos.flow
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private var newPagingPhotosSource: PagingSource<*, *>? = null
}