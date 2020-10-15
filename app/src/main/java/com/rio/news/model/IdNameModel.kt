package com.rio.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class IdNameModel(
    var id: String,
    var name: String): Parcelable