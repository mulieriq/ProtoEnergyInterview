package com.skylabstechke.protoenergyinterview.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.skylabstechke.protoenergyinterview.data.repository.Repository

import com.skylabstechke.protoenergyinterview.models.OrdersModelItem
import com.skylabstechke.protoenergyinterview.utils.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: Repository
) :
    AndroidViewModel(application) {
    var orderResponse: MutableLiveData<NetworkResult<MutableList<OrdersModelItem>>> = MutableLiveData()

    fun getOrders() = viewModelScope.launch {
        getOrdersSafeCall()
    }


    private suspend fun getOrdersSafeCall() {
        orderResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val apiOrderResponse = repository.getOrders().asLiveData()
                orderResponse.value = handleApiOrderResponse(apiOrderResponse)
            } catch (e: Exception) {
                orderResponse.value = NetworkResult.Error(e.message.toString())
            }
        }else{
            orderResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }


    private fun handleApiOrderResponse(apiOrderResponse: Response<MutableList<OrdersModelItem>>): NetworkResult<MutableList<OrdersModelItem>> {

        when {
            apiOrderResponse.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            apiOrderResponse.body()!!.isEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            apiOrderResponse.isSuccessful -> {
                val orders = apiOrderResponse.body()
                return NetworkResult.Success(orders!!)

            }
            else -> {
                return NetworkResult.Error(apiOrderResponse.message())
            }
        }

    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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