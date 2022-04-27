package com.patel.manpatelcbc.adapters

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patel.manpatelcbc.DetailActivity
import com.patel.manpatelcbc.R
import com.patel.manpatelcbc.model.NewsModelItems
import kotlinx.android.synthetic.main.news_item.view.*
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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



    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(publishedDate: Long?): String {
        val sdf = SimpleDateFormat("MMMM dd, yyyy - hh:mm:ss", Locale.CANADA)

        val newsDate = publishedDate?.let { Date(it).toInstant() }
        val now: Instant = Instant.now()
        val twelveHoursEarlier = now.minus(12, ChronoUnit.HOURS)
        val within12Hours = !newsDate?.isBefore(twelveHoursEarlier)!! && newsDate.isBefore(now)

        return if (within12Hours) {
            val d = Duration.between(newsDate, now)
            if (d.toHours() == 0L) {
                "Now"
            } else {
                "An hour ago"
            }
        } else {
            sdf.format(publishedDate?.let { Date(it) })
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((NewsModelItems) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(news.typeAttributes?.imageLarge).into(newsImage)
            headline.text = news.title
            publishedDate.text = news.publishedAt.let{formatDate(it)}

            //onClick
            setOnClickListener {
                onItemClickListener?.let { it(news) }
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("URL", news.typeAttributes?.url)
                //Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
                context.startActivity(intent)
            }
        }
    }
    fun setOnItemClickListener(listener: (NewsModelItems) -> Unit) {
        onItemClickListener = listener
    }
}