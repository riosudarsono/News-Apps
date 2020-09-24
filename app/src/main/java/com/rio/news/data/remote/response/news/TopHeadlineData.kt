package com.rio.news.data.remote.response.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TopHeadlineData(
    var source: Source?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?): Parcelable {

    @Parcelize
    class Source(
        var id: String?,
        var name: String?): Parcelable
}