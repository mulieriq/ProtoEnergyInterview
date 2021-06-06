package com.skylabstechke.protoenergyinterview.data.network

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProtoEnergyApi {
    @GET("/api/data")
    suspend fun getEntries(
        @QueryMap queries :Map<String,String>
    )
}