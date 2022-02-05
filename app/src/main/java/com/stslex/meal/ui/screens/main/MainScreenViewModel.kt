package com.stslex.meal.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.core.Mapper
import com.stslex.meal.data.entity.ImageEntity
import com.stslex.meal.domain.repository.PhotosRepository
import com.stslex.meal.ui.model.ImageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val mapper: Mapper.Data<PagingData<ImageEntity>, Flow<PagingData<ImageModel>>>
) : ViewModel() {

    @ExperimentalCoroutinesApi
    fun getAllPhotos(): Flow<PagingData<ImageModel>> =
        photosRepository.getAllPhotos().flatMapLatest(mapper::map)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}