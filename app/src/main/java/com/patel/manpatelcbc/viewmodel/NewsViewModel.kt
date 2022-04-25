package com.patel.manpatelcbc.viewmodel

import com.patel.manpatelcbc.NewsApplication
import com.patel.manpatelcbc.model.NewsModel
import com.patel.manpatelcbc.repository.NewsRepository
import com.patel.manpatelcbc.util.Resource



import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    app: Application,
    val newsRepository: NewsRepository
) : AndroidViewModel(app) {


    val breakingNews: MutableLiveData<Resource<NewsModel>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsModel? = null

    val searchNews: MutableLiveData<Resource<NewsModel>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsModel? = null
    var newSearchQuery:String? = null
    var oldSearchQuery:String? = null


    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        //   safeBreakingNewsCall(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        //   safeSearchNewsCall(searchQuery)
    }

    private fun handleBreakingNewsResponse(response: Response<NewsModel>) : Resource<NewsModel> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldNews = breakingNewsResponse?.newsModel
                    val newNews = resultResponse.newsModel
                    oldNews?.addAll(newNews)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }




    private fun handleSearchNewsResponse(response: Response<NewsModel>) : Resource<NewsModel> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(searchNewsResponse == null || newSearchQuery != oldSearchQuery) {
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                } else {
                    searchNewsPage++
                    val oldNews = searchNewsResponse?.newsModel
                    val newNews = resultResponse.newsModel
                    oldNews?.addAll(newNews)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

//    fun saveArticle(article: NewsModelItem) = viewModelScope.launch {
//        newsRepository.upsert(article)
//    }
//
//    fun getSavedNews() = newsRepository.getSavedNews()
//
//    fun deleteArticle(article: Article) = viewModelScope.launch {
//        newsRepository.deleteArticle(article)
//    }

//    private suspend fun safeSearchNewsCall(searchQuery: String) {
//        newSearchQuery = searchQuery
//        searchNews.postValue(Resource.Loading())
//        try {
//            if(hasInternetConnection()) {
//                val response = newsRepository.searchNews(searchQuery, searchNewsPage)
//                searchNews.postValue(handleSearchNewsResponse(response))
//            } else {
//                searchNews.postValue(Resource.Error("No internet connection"))
//            }
//        } catch(t: Throwable) {
//            when(t) {
//                is IOException -> searchNews.postValue(Resource.Error("Network Failure"))
//                else -> searchNews.postValue(Resource.Error("Conversion Error"))
//            }
//        }
//    }

//    private suspend fun safeBreakingNewsCall(countryCode: String) {
//        breakingNews.postValue(Resource.Loading())
//        try {
//            if(hasInternetConnection()) {
//                val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
//                breakingNews.postValue(handleBreakingNewsResponse(response))
//            } else {
//                breakingNews.postValue(Resource.Error("No internet connection"))
//            }
//        } catch(t: Throwable) {
//            when(t) {
//                is IOException -> breakingNews.postValue(Resource.Error("Network Failure"))
//                else -> breakingNews.postValue(Resource.Error("Conversion Error"))
//            }
//        }
//    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}