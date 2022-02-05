package com.stslex.meal.data.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stslex.meal.BuildConfig.API_KEY
import com.stslex.meal.data.model.RemoteImageModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class PhotosPagingSource @AssistedInject constructor(
    private val service: PhotosService
) : PagingSource<Int, RemoteImageModel>() {

    override fun getRefreshKey(state: PagingState<Int, RemoteImageModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteImageModel> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize

            val response = service.getPhotos(
                page = pageNumber,
                page_size = pageSize,
                api_key = API_KEY
            )

            return if (response.isSuccessful) {
                val photos = response.body()!!
                val nextPageNumber = if (photos.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(photos, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): PhotosPagingSource
    }

    companion object {
        private const val INITIAL_PAGE_NUMBER = 10
    }
}