package com.rio.news.ui.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rio.news.R
import com.rio.news.utils.BaseApp
import com.rio.news.utils.Constants
import com.rio.news.utils.toast
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity(), BaseApp.Listener {

    private var link: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        BaseApp().set(this)
    }

    override fun getIntentData() {
        if (intent.hasExtra(Constants.DATA_EXTRA)){
            link = intent.getStringExtra(Constants.DATA_EXTRA)
        } else {
            toast(getString(R.string.data_empty))
            finish()
        }
    }

    override fun setOnClick() {
        iv_back.setOnClickListener { onBackPressed() }
    }

    override fun setAdapter() {}

    override fun setContent() {
        setWebView(link)
    }

    override fun loadData() {}

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView(link: String?) {
        webView.settings.javaScriptEnabled = true
        val webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.domStorageEnabled = true
        webSettings.setGeolocationEnabled(true)
        webView.isSoundEffectsEnabled = true
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progressBar.progress = newProgress
                if (newProgress == 100) progressBar.hide() else progressBar.show()
            }
        }
        webView.webViewClient = WebViewClient()
        webView.loadUrl(link)
    }
}