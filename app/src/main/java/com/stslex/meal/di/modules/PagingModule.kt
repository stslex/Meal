package com.stslex.meal.di.modules

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides

@Module
class PagingModule {

    @Provides
    fun providesDefaultPagingConfig(): PagingConfig = PagingConfig(
        pageSize = DEFAULT_PAGE_SIZE,
        enablePlaceholders = PLACE_HOLDER_ENABLE
    )

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
        private const val PLACE_HOLDER_ENABLE = false
    }
}