package com.stslex.meal.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.stslex.meal.data.mappers.PagingImageMapper
import com.stslex.meal.data.photos.PhotosRepository
import com.stslex.meal.ui.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val mapper: PagingImageMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    fun getAllPhotos(): StateFlow<PagingData<ImageModel>> =
        photosRepository.listenAllPhotos().flatMapLatest(mapper::map).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )
}