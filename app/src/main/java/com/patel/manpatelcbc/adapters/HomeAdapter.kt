package com.patel.manpatelcbc.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patel.manpatelcbc.R
import com.patel.manpatelcbc.model.NewsModelItem
import com.patel.manpatelcbc.model.NewsModelItems
import java.util.ArrayList

class HomeAdapter(mContext: Context, mActivity: Activity, mContentList: ArrayList<NewsModelItems?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mContext: Context
    private val mActivity: Activity
    private val mContentList: ArrayList<NewsModelItems?>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view, viewType)
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        val pDate : TextView
        val headline : TextView
        val image : ImageView

        init {
            // Find all views ids
            image = itemView.findViewById(R.id.newsImage)
            headline = itemView.findViewById(R.id.headline)
            pDate = itemView.findViewById(R.id.publishedDate)


        }
    }

    override fun onBindViewHolder(mainHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = mainHolder as ViewHolder
        val model: NewsModelItems? = mContentList?.get(position)
        // setting data over views
        val imgUrl: String? = model?.images?.square_140
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                .load(imgUrl)
                .into(holder.image)
        }
        if (model != null) {
            holder.headline.setText(model.title)
        }
        if (model != null) {
            holder.pDate.setText(model.readablePublishedAt)
        }

    }

    override fun getItemCount(): Int {
        if (mContentList != null) {
            return mContentList.size
        }
        return 0
    }

    init {
        this.mContext = mContext
        this.mActivity = mActivity
        this.mContentList = mContentList
    }
}