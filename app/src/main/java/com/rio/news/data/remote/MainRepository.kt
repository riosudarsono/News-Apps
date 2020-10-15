package com.rio.news.data.remote

import androidx.lifecycle.MutableLiveData
import com.rio.news.BuildConfig
import com.rio.news.data.remote.response.BaseResponse
import com.rio.news.data.remote.response.news.TopHeadlineData
import com.rio.news.utils.Constants
import com.rio.news.utils.EspressoIdlingResource
import com.rio.news.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainRepository(private val mainService: MainService) {
    private val compositeDisposable = CompositeDisposable()

    fun topHeadlines(query: String, page: Int, category: String): MutableLiveData<Resource<BaseResponse<MutableList<TopHeadlineData>>>>{
        EspressoIdlingResource.increment()
        val data = MutableLiveData<Resource<BaseResponse<MutableList<TopHeadlineData>>>>()
        data.value = Resource.loading(null)

        mainService.topHeadlines(BuildConfig.API_KEY, Constants.COUNTRY_ID, category, Constants.PAGE_SIZE, page, query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    if (it.status == Constants.RESPONSE_OK) data.value = Resource.success(it)
                    else data.value = Resource.error(it.message, it)
                    EspressoIdlingResource.decrement()
                }, {
                    it.printStackTrace()
                    data.value = Resource.error(it.message, null)
                    EspressoIdlingResource.decrement()
                }
            ).addTo(disposable = compositeDisposable)
        return data
    }


    private fun Disposable.addTo(disposable: CompositeDisposable) = disposable.add(this)
    fun onDestroy(){
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}