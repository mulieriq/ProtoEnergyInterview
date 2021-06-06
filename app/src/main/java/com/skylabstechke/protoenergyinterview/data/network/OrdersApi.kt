package com.skylabstechke.protoenergyinterview.data.network

import com.skylabstechke.protoenergyinterview.models.OrdersModel
import retrofit2.Response
import retrofit2.http.GET

interface OrdersApi {
    @GET("/api/data")
    suspend fun getOrders(): Response<OrdersModel>
}