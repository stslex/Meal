package com.stslex.meal.di.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.stslex.meal.di.scope.ActiveNetwork
import com.stslex.meal.di.scope.OfflineInterceptor
import com.stslex.meal.di.scope.OnlineInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class RetrofitModule {

    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesRetrofitClient(
        mLoggingInterceptor: HttpLoggingInterceptor,
        @OnlineInterceptor onlineInterceptor: Interceptor,
        @OfflineInterceptor offlineInterceptor: Interceptor,
        @ActiveNetwork activeNetwork: Boolean,
        cache: Cache
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(mLoggingInterceptor)
        .addInterceptor(if (activeNetwork) onlineInterceptor else offlineInterceptor)
        .cache(cache)
        .build()

    @Provides
    fun providesCacheDir(application: Application): Cache = Cache(application.cacheDir, CACHE_SIZE)

    @Provides
    @ActiveNetwork
    fun checkNetwork(application: Application): Boolean {
        (application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            val network = activeNetwork ?: return false
            val activeNetwork = getNetworkCapabilities(network) ?: return false
            return activeNetwork.checkTransport
        }
    }

    private val NetworkCapabilities.checkTransport: Boolean
        get() = when {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @OnlineInterceptor
    fun providesOnlineInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        response.newBuilder()
            .header(HEADER_NAME, HEADER_ONLINE_VALUE)
            .removeHeader(HEADER_PRAGMA)
            .build()
    }

    @Provides
    @OfflineInterceptor
    fun providesOfflineInterceptor(): Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        request = request.newBuilder()
            .header(HEADER_NAME, HEADER_OFFLINE_VALUE)
            .removeHeader(HEADER_PRAGMA)
            .build()
        chain.proceed(request)
    }

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"
        private const val MAX_STALE: Int = 60 * 60 * 12
        private const val MAX_AGE: Int = 60 * 60 * 3
        private const val HEADER_NAME = "Cache-Control"
        private const val HEADER_OFFLINE_VALUE = "public, only-if-cached, max-stale=$MAX_STALE"
        private const val HEADER_ONLINE_VALUE = "public, max-age=$MAX_AGE"
        private const val HEADER_PRAGMA = "Pragma"
        private const val CACHE_SIZE: Long = 10 * 1024 * 1024 * 8L
    }
}