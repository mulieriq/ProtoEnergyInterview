package com.skylabstechke.protoenergyinterview.data.network

import retrofit2.Response
import retrofit2.http.GET

interface OrdersApi {
    @GET("api/v1/orders.json")
    suspend fun getOrders(): Response<OrdersModel>
}