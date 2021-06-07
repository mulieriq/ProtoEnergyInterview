package com.skylabstechke.protoenergyinterview.data.repository

import com.skylabstechke.protoenergyinterview.data.network.OrdersApi
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val ordersApi: OrdersApi) {
    suspend fun getOrders(): Response<OrdersModel> {
        return ordersApi.getOrders()
    }
}