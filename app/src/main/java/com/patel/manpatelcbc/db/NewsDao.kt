package com.patel.manpatelcbc.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.patel.manpatelcbc.model.NewsModelItem

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(news: NewsModelItem): Long

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsModelItem>>

    @Delete
    suspend fun deleteNews(news: NewsModelItem)
}