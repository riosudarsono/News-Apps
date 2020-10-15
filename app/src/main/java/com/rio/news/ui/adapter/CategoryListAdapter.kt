package com.rio.news.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rio.news.R
import com.rio.news.model.IdNameModel
import com.rio.news.ui.list.ListActivity
import com.rio.news.utils.Constants
import kotlinx.android.synthetic.main.view_item_category.view.*

class CategoryListAdapter(private val activity: Activity, private var data: MutableList<IdNameModel>) : RecyclerView.Adapter<CategoryListAdapter.Holder>() {

    fun setItems(data: MutableList<IdNameModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addItems(data: MutableList<IdNameModel>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_category, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mData: IdNameModel, position: Int) {
            with(itemView) {
                tv_name.text = mData.name

                itemView.setOnClickListener {
                    val i = Intent(activity, ListActivity::class.java)
                    i.putExtra(Constants.DATA_EXTRA, mData)
                    activity.startActivity(i)
                }
            }
        }

    }


}