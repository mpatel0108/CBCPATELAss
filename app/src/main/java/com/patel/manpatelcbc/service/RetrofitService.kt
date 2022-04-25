package com.patel.manpatelcbc.service

import com.patel.manpatelcbc.model.NewsModel
import com.patel.manpatelcbc.model.NewsModelItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
//    @GET("items")
//    suspend fun getTopNews(
//        @Query("lineupSlug")
//        lineupSlug: String = "news",
//    ): Response<NewsModel>

    @GET("items")
    suspend fun getTopNews(
        @Query("lineupSlug")
        lineupSlug: String = "news",
    ): Response<List<NewsModelItems?>?>?
}