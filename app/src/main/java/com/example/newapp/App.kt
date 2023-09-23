package com.example.newapp

import android.app.Application
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
            internal set

        fun hasNetwork() = instance.isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }
}