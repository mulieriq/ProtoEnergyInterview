package com.skylabstechke.protoenergyinterview.data.network

import com.skylabstechke.protoenergyinterview.models.MockData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface OrdersApi
{
    @GET("/api/data")
    suspend fun getOrders(
        @QueryMap queries :Map<String,String>
    ):Response<MockData>
}