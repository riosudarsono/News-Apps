package com.rio.news.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rio.news.R
import com.rio.news.model.IdNameModel
import com.rio.news.ui.MainViewModel
import com.rio.news.ui.adapter.NewsListAdapter
import com.rio.news.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.layout_error.*

@AndroidEntryPoint
class ListActivity : AppCompatActivity(), BaseApp.Listener, SearchView.OnQueryTextListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var newsAdapter: NewsListAdapter

    private var nextPage = true
    private var loading = false
    private var page = 1
    private var query = ""

    private var category: IdNameModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        BaseApp().set(this)
    }

    override fun getIntentData() {
        if (intent.hasExtra(Constants.DATA_EXTRA)){
            category = intent.getParcelableExtra(Constants.DATA_EXTRA)
        } else {
            toast(getString(R.string.data_empty))
            finish()
        }
    }

    override fun setOnClick() {
        iv_back.setOnClickListener { onBackPressed() }
        tv_retry.setOnClickListener {
            page = 1
            loadData()
        }
    }

    override fun setAdapter() {
        newsAdapter = NewsListAdapter(this, ArrayList())
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_content.adapter = newsAdapter
        rv_content.layoutManager = layoutManager
        rv_content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val countItem = layoutManager.itemCount
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (nextPage) {
                    if (!loading && isLastPosition) {
                        page++
                        loadData()
                    }
                }
            }
        })


    }

    override fun setContent() {
        tv_title.text = getString(R.string.berita_, category?.name)
        searchView.setOnQueryTextListener(this)

        srl_content.setColorSchemeColors(*resources.getIntArray(R.array.intarr_swipe_refresh_layout))
        srl_content.setOnRefreshListener {
            page = 1
            loadData()
        }
    }

    override fun loadData() {
        viewModel.topHeadlines(query, page, category!!.id).observe(this, Observer {
            if (it != null){
                when (it.status){
                    Status.LOADING -> {
                        loading = true
                        srl_content.isRefreshing = true
                    }
                    Status.SUCCESS -> {
                        loading = false
                        srl_content.isRefreshing = false
                        layout_error.hide()
                        nextPage = it.data!!.articles.isNotEmpty()
                        if (page == 1)newsAdapter.setItems(it.data.articles)
                        else newsAdapter.addItems(it.data.articles)
                    }
                    Status.ERROR -> {
                        loading = false
                        srl_content.isRefreshing = false
                        layout_error.show()
                        tv_message.text = it.message
                    }
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        this.query = query!!
        page = 1
        nextPage = true
        loadData()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return if (newText!!.isEmpty()){
            this.query = newText
            page = 1
            nextPage = true
            loadData()
            true
        } else false
    }
}