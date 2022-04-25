package com.patel.manpatelcbc.repository

import com.patel.manpatelcbc.db.NewsDatabase
import com.patel.manpatelcbc.model.NewsModelItem
import com.patel.manpatelcbc.service.RetrofitInstance

class NewsRepository(
    val db: NewsDatabase
)  {

    suspend fun getBreakingNews(lineupSlug: String) =
        RetrofitInstance.api.getTopNews(lineupSlug)

    suspend fun getFilterBreakingNews(type: String) =
        RetrofitInstance.api.getTopFilterNews(type)

//    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
//        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(news: NewsModelItem) = db.getArticleDao().upsert(news)

    fun getSavedNews() = db.getArticleDao().getAllNews()

    suspend fun deleteArticle(news: NewsModelItem) = db.getArticleDao().deleteNews(news)
}