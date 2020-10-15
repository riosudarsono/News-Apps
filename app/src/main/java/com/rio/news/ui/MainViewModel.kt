package com.rio.news.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rio.news.data.remote.MainRepository
import com.rio.news.data.remote.MainService
import com.rio.news.data.remote.response.BaseResponse
import com.rio.news.data.remote.response.news.TopHeadlineData
import com.rio.news.utils.Resource

class MainViewModel @ViewModelInject constructor(mainService: MainService) : ViewModel() {
    private val repo = MainRepository(mainService)

    fun topHeadlines(query: String, page: Int, category: String): LiveData<Resource<BaseResponse<MutableList<TopHeadlineData>>>>
            = repo.topHeadlines(query, page, category)

    override fun onCleared() {
        super.onCleared()
        repo.onDestroy()
    }
}