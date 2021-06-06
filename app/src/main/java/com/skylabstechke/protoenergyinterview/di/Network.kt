package com.skylabstechke.protoenergyinterview.di

import android.app.Application
import com.skylabstechke.protoenergyinterview.data.network.ProtoEnergyApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.create
import java.lang.annotation.RetentionPolicy

@Module
@InstallIn(ApplicationComponent::class)
object Network {


    fun provideApiService(retrofit: Retrofit):ProtoEnergyApi{
        return  retrofit.create(ProtoEnergyApi::class.java)
    }


}