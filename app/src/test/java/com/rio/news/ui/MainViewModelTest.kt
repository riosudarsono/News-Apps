package com.rio.news.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.rio.news.data.remote.MainRepository
import com.rio.news.data.remote.MainService
import com.rio.news.data.remote.response.BaseResponse
import com.rio.news.data.remote.response.news.TopHeadlineData
import com.rio.news.utils.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainService: MainService
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer: Observer<Resource<BaseResponse<MutableList<TopHeadlineData>>>>

    @Mock
    private lateinit var dataList: BaseResponse<MutableList<TopHeadlineData>>

    @Before
    fun setUp() {
        mainService = mock()
        viewModel = MainViewModel(mainService)
    }

    @Test
    fun topHeadlines() {
        val dummy = Resource.success(dataList)
        `when`(dummy.data?.articles?.size).thenReturn(5)
        val news = MutableLiveData<Resource<BaseResponse<MutableList<TopHeadlineData>>>>()
        news.value = dummy

//        `when`(mainRepository.topHeadlines(1)).thenReturn(news)
//        val newsEntities = viewModel.topHeadlines(1).value?.data?.articles
//        Mockito.verify(mainRepository).topHeadlines(1)
//        Assert.assertNotNull(newsEntities)
//        Assert.assertEquals(5, newsEntities?.size)
//
//        viewModel.topHeadlines(1).observeForever(observer)
//        Mockito.verify(observer).onChanged(dummy)
    }
}