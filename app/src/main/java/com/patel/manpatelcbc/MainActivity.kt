package com.patel.manpatelcbc


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patel.manpatelcbc.adapters.NewsAdapter
import com.patel.manpatelcbc.db.NewsDatabase
import com.patel.manpatelcbc.repository.NewsRepository
import com.patel.manpatelcbc.util.Resource
import com.patel.manpatelcbc.viewmodel.NewsViewModel
import com.patel.manpatelcbc.viewmodel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_error_message.*

class MainActivity : AppCompatActivity() {
   lateinit var viewModel: NewsViewModel

    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()


        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)


        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        viewModel.breakingNews.observe(this) {
           when(it) {
        is Resource.Success -> {
            hideProgressBar()
            hideErrorMessage()
           it.data?.let { newsResponse ->
                newsAdapter.differ.submitList(newsResponse.toList())
                val totalPages = newsResponse.size / QUERY_PAGE_SIZE + 2
                isLastPage = viewModel.breakingNewsPage == totalPages
                if(isLastPage) {
                    rcvNews.setPadding(0, 0, 0, 0)
                }
            }
        }
        is Resource.Error -> {
            hideProgressBar()
            it.message?.let { message ->
                Toast.makeText(this, "An error occured: $message", Toast.LENGTH_LONG).show()
                showErrorMessage(message)
            }
        }
        is Resource.Loading -> {
            showProgressBar()
        }
    }

}

        viewModel.filterBreakingNews.observe(this) {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    it.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.toList())
                        val totalPages = newsResponse.size / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages
                        if(isLastPage) {
                            rcvNews.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message ->
                        Toast.makeText(this, "An error occured: $message", Toast.LENGTH_LONG).show()
                        showErrorMessage(message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }





        btnRetry.setOnClickListener {
            viewModel.getBreakingNews("news")
        }
        filterBtn.setOnClickListener {
   showOptionsDialog()
        }

    }

    private fun showOptionsDialog() {
        val array = arrayOf( "video" ,"stub", "story", "image")
        lateinit var dialog: androidx.appcompat.app.AlertDialog
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)


        builder.setSingleChoiceItems(array, -1) {_, which ->
            val type = array[which]
            try {
                Log.d("mainactivity", "$type  selected.")
                viewModel.getFilterBreakingNews(type)
            } catch (e: IllegalArgumentException) {

            }
            dialog.dismiss()
        }
        dialog = builder.create()

        dialog.show()
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        itemErrorMessage.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        itemErrorMessage.visibility = View.VISIBLE
        tvErrorMessage.text = message
        isError = true
    }
    val QUERY_PAGE_SIZE = 20
    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoErrors = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNoErrors && isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
               viewModel.getBreakingNews("news")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rcvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(this@MainActivity.scrollListener)
        }
    }
}