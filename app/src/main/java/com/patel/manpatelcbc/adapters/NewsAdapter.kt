package com.patel.manpatelcbc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patel.manpatelcbc.R
import com.patel.manpatelcbc.model.NewsModelItem
import com.patel.manpatelcbc.model.NewsModelItems
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<NewsModelItems>() {
        override fun areItemsTheSame(oldItem: NewsModelItems, newItem: NewsModelItems): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsModelItems, newItem: NewsModelItems): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((NewsModelItems) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(news.images?.square_140).into(newsImage)
            headline.text = news.title
            publishedDate.text = news.readablePublishedAt


            setOnClickListener {
                onItemClickListener?.let { it(news) }
            }
        }
    }

    fun setOnItemClickListener(listener: (NewsModelItems) -> Unit) {
        onItemClickListener = listener
    }
}
