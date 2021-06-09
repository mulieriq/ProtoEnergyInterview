package com.skylabstechke.protoenergyinterview.data.network


import com.skylabstechke.protoenergyinterview.models.OrdersModelItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface OrdersApi {
    @GET("api/v1/orders.json")
    suspend fun getOrders(): Response<MutableList<OrdersModelItem>>
}