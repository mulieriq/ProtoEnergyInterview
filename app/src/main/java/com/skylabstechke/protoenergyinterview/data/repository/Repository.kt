package com.skylabstechke.protoenergyinterview.data.repository

import com.skylabstechke.protoenergyinterview.data.network.OrdersApi
import com.skylabstechke.protoenergyinterview.models.MockData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val ordersApi: OrdersApi) {
    suspend fun getOrders(queries: Map<String, String>): Response<MockData> {
        return ordersApi.getOrders(queries)
    }
}