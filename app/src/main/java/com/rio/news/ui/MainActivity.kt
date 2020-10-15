package com.rio.news.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rio.news.R
import com.rio.news.model.IdNameModel
import com.rio.news.ui.adapter.CategoryListAdapter
import com.rio.news.utils.BaseApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseApp.Listener {
    private lateinit var categoryListAdapter: CategoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseApp().set(this)
    }

    override fun getIntentData() {}

    override fun setOnClick() {}

    override fun setAdapter() {
        categoryListAdapter = CategoryListAdapter(this, ArrayList())
        rv_content.adapter = categoryListAdapter
        rv_content.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun setContent() {
        val data: MutableList<IdNameModel> = ArrayList()
        data.add(IdNameModel("business", getString(R.string.business)))
        data.add(IdNameModel("entertainment", getString(R.string.entertainment)))
        data.add(IdNameModel("general", getString(R.string.general)))
        data.add(IdNameModel("health", getString(R.string.health)))
        data.add(IdNameModel("science", getString(R.string.science)))
        data.add(IdNameModel("sports", getString(R.string.sports)))
        data.add(IdNameModel("technology", getString(R.string.technology)))
        categoryListAdapter.setItems(data)
    }

    override fun loadData() {}

}