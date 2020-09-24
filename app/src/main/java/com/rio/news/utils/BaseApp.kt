package com.rio.news.utils

class BaseApp {
    fun set(listener: Listener){
        listener.getIntentData()
        listener.setOnClick()
        listener.setAdapter()
        listener.setContent()
        listener.loadData()}
    interface Listener {
        fun getIntentData()
        fun setOnClick()
        fun setAdapter()
        fun setContent()
        fun loadData()
    }

}