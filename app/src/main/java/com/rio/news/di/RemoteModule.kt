package com.rio.news.di

import com.google.common.net.HttpHeaders
import com.rio.news.BuildConfig
import com.rio.news.data.remote.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(ApplicationComponent::class)
@Module
object RemoteModule {

    @Provides
    fun provideRetrofit(): Retrofit{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val cacheControl = CacheControl.Builder()
            .maxAge(5, TimeUnit.SECONDS)
            .build()

        val okhttp = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .connectTimeout(240, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .apply {
                addInterceptor(Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                        .header(HttpHeaders.CACHE_CONTROL, cacheControl.toString())
//                        .addHeader(HttpHeaders.AUTHORIZATION, authStringToken)
                        .header(HttpHeaders.CONTENT_TYPE, "application/json")
                        .addHeader(HttpHeaders.CONNECTION, "close")
                    return@Interceptor chain.proceed(builder.build())
                })
            }
            .build()

        val endPoint = BuildConfig.API_URL

        return Retrofit.Builder().baseUrl(endPoint)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideMainService(retrofit: Retrofit) : MainService {
        return retrofit.create(MainService::class.java)
    }

}