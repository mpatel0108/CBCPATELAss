package com.patel.manpatelcbc.service

import com.patel.manpatelcbc.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("items?lineupSlug=news")
    suspend fun getTopNews(

    ): Response<NewsModel>
}