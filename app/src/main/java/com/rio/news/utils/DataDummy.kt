package com.rio.news.utils

import com.rio.news.data.remote.response.news.TopHeadlineData

object DataDummy {
    fun generateDummy(): MutableList<TopHeadlineData>{
        val news = ArrayList<TopHeadlineData>()
        news.add(TopHeadlineData(TopHeadlineData.Source("Rio", "Rio"), "Rio","Rio","Rio","Rio","Rio","Rio","Rio"))
        news.add(TopHeadlineData(TopHeadlineData.Source("1", "1"), "1","1","1","1","1","1","1"))
        news.add(TopHeadlineData(TopHeadlineData.Source("1", "1"), "1","1","1","1","1","1","1"))
        news.add(TopHeadlineData(TopHeadlineData.Source("1", "1"), "1","1","1","1","1","1","1"))
        news.add(TopHeadlineData(TopHeadlineData.Source("1", "1"), "1","1","1","1","1","1","1"))
        return news
    }
}