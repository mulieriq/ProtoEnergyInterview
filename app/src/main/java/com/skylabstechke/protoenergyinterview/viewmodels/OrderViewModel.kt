package com.skylabstechke.protoenergyinterview.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skylabstechke.protoenergyinterview.data.repository.Repository
import com.skylabstechke.protoenergyinterview.models.OrdersModelItem
import com.skylabstechke.protoenergyinterview.utils.NetworkCheck
import com.skylabstechke.protoenergyinterview.utils.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: Repository,
    private val networkCheck: NetworkCheck
) :
    AndroidViewModel(application) {
    var orderResponse: MutableLiveData<NetworkResult<List<OrdersModelItem>>> = MutableLiveData()

    fun getOrders() = viewModelScope.launch {
        getOrdersSafeCall()
    }


    private suspend fun getOrdersSafeCall() {
        orderResponse.value = NetworkResult.Loading()
        if (networkCheck.hasInternetConnection()) {
            try {
                val apiOrderResponse = repository.getOrders()


                orderResponse.value = handleApiOrderResponse(apiOrderResponse)


            } catch (e: Exception) {
                orderResponse.value = NetworkResult.Error(e.message.toString())
            }
        } else {
            orderResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }


    private fun handleApiOrderResponse(apiOrderResponse: Response<List<OrdersModelItem>>): NetworkResult<List<OrdersModelItem>> {

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


}