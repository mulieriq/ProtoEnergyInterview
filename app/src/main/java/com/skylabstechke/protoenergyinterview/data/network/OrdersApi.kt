package com.skylabstechke.protoenergyinterview.data.network

import com.skylabstechke.protoenergyinterview.models.OrdersModel
import com.skylabstechke.protoenergyinterview.models.OrdersModelItem
import retrofit2.Response
import retrofit2.http.GET

interface OrdersApi {
    @GET("api/v1/orders.json")
    suspend fun getOrders(): Response<List<OrdersModelItem>>
}