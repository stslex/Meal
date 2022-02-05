package com.stslex.meal.data.photos

import com.stslex.meal.data.model.RemoteImageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {

    @GET(GET_PHOTOS)
    suspend fun getPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    companion object {
        private const val GET_PHOTOS = "photos"
        private const val QUERY_PAGE = "page"
        private const val QUERY_API_KEY = "client_id"
        private const val QUERY_PAGE_SIZE = "per_page"
    }
}