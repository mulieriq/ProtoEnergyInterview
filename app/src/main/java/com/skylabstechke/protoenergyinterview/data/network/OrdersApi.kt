package com.skylabstechke.protoenergyinterview.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface OrdersApi {
    @GET("/api/data")
    suspend fun getOrders(
        @QueryMap queries: Map<String, String>
    ): Response<MockData>
}