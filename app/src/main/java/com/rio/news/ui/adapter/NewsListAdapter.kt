package com.rio.news.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rio.news.R
import com.rio.news.data.remote.response.news.TopHeadlineData
import com.rio.news.ui.detail.DetailActivity
import com.rio.news.utils.Constants
import com.rio.news.utils.Utils
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.view_item_news_list.view.*
import kotlinx.android.synthetic.main.view_item_news_list.view.iv_image
import kotlinx.android.synthetic.main.view_item_news_list.view.tv_desc
import kotlinx.android.synthetic.main.view_item_news_list.view.tv_title

class NewsListAdapter(private val activity: Activity, private var data: MutableList<TopHeadlineData>) : RecyclerView.Adapter<NewsListAdapter.Holder>() {

    fun setItems(data: MutableList<TopHeadlineData>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addItems(data: MutableList<TopHeadlineData>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_news_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mData: TopHeadlineData, position: Int) {
            with(itemView) {
                Utils.setGlide(activity, mData.urlToImage, iv_image)
                tv_title.text = mData.title
                tv_desc.text = mData.description

                itemView.setOnClickListener {
                    val i = Intent(activity, DetailActivity::class.java)
                    i.putExtra(Constants.DATA_EXTRA, mData)
                    activity.startActivity(i)
                }
            }
        }

    }


}