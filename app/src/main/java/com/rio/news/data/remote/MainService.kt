package com.rio.news.data.remote

import com.rio.news.data.remote.response.BaseResponse
import com.rio.news.data.remote.response.news.TopHeadlineData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("v2/top-headlines")
    fun topHeadlines(@Query("apiKey") apiKey: String, @Query("country") country: String,
                     @Query("category") category: String, @Query("pageSize") pageSize: Int,
                     @Query("page") page: Int, @Query("q") query: String): Observable<BaseResponse<MutableList<TopHeadlineData>>>
}