package com.rio.news.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rio.news.R
import com.rio.news.data.remote.response.news.TopHeadlineData
import com.rio.news.utils.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), BaseApp.Listener {

    private var data: TopHeadlineData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        BaseApp().set(this)
    }

    override fun getIntentData() {
        if (intent.hasExtra(Constants.DATA_EXTRA)){
            data = intent.getParcelableExtra(Constants.DATA_EXTRA)
        } else {
            toast(getString(R.string.data_empty))
            finish()
        }
    }

    override fun setOnClick() {
        iv_back.setOnClickListener { onBackPressed() }
        tv_url.setOnClickListener { Utils.openLink(data?.url!!, this) }
    }

    override fun setAdapter() {}

    override fun setContent() {
        if (data != null){
            Utils.setGlide(this, data?.urlToImage, iv_image)
            tv_title.text = data?.title
            tv_author.text = data?.author
            tv_date.text = Utils.convertDate(data?.publishedAt)
            tv_desc.text = data?.description

            if (data?.url.isNullOrEmpty()) tv_url.hide()
            else tv_url.show()
        }
    }

    override fun loadData() {}
}