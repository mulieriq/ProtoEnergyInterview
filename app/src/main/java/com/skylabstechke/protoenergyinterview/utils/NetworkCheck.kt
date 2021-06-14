package com.skylabstechke.protoenergyinterview.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.scopes.ActivityRetainedScoped


@ActivityRetainedScoped
class NetworkCheck(context: Context) {
    private val application = context.applicationContext
    fun hasInternetConnection(): Boolean {
        val connectivityManager = application
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetWork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetWork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}