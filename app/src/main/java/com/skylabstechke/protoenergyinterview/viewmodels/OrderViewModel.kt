package com.skylabstechke.protoenergyinterview.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skylabstechke.protoenergyinterview.data.repository.Repository
import com.skylabstechke.protoenergyinterview.models.OrdersModelItem
import com.skylabstechke.protoenergyinterview.utils.NetworkCheck
import com.skylabstechke.protoenergyinterview.utils.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderViewModel @ViewModelInject constructor(
    private val repository: Repository,
    private val networkStats: NetworkCheck
) :
    ViewModel() {
    var orderResponse: MutableLiveData<NetworkResult<List<OrdersModelItem>>> = MutableLiveData()

    fun getOrders() = viewModelScope.launch {
        getOrdersSafeCall()
    }

    private suspend fun getOrdersSafeCall() {
        orderResponse.value = NetworkResult.Loading()
        if (networkStats.hasInternetConnection()) {
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
        return when {
            apiOrderResponse.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            apiOrderResponse.body()!!.isEmpty() -> {
                NetworkResult.Error("Recipes not found.")
            }
            apiOrderResponse.isSuccessful -> {
                val orders = apiOrderResponse.body()
                NetworkResult.Success(orders!!)
            }
            else -> {
                NetworkResult.Error(apiOrderResponse.message())
            }
        }
    }
}