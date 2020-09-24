package com.rio.news.data.remote.response

class BaseResponse<T>(
    var status: String,
    var totalResults: Int,
    var articles: T,
    var code: String,
    var message: String)