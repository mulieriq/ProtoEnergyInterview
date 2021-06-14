//package com.skylabstechke.protoenergyinterview.utils
//
//import android.app.Application
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkCapabilities
//
//class NetworkCheck {
//    private val application: Application = Application()
//    fun hasInternetConnection(): Boolean {
//        val connectivityManager = application
//            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetWork = connectivityManager.activeNetwork ?: return false
//        val capabilities = connectivityManager.getNetworkCapabilities(activeNetWork) ?: return false
//
//        return when {
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> false
//        }
//    }
//}