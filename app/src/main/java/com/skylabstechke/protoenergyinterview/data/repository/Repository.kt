package com.skylabstechke.protoenergyinterview.data.repository

import com.skylabstechke.protoenergyinterview.data.network.OrdersApi


import com.skylabstechke.protoenergyinterview.models.OrdersModelItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val ordersApi: OrdersApi) {
    suspend fun getOrders(): Response<MutableList<OrdersModelItem>> {
        return ordersApi.getOrders()
    }
}