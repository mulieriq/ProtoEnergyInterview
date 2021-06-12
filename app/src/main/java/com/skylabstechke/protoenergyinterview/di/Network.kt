package com.skylabstechke.protoenergyinterview.di


import com.skylabstechke.protoenergyinterview.data.network.OrdersApi
import com.skylabstechke.protoenergyinterview.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object Network {
    @Singleton
    @Provides
    fun provideMoshiInstance():Moshi{
        return Moshi.Builder().build()
    }
    @Singleton
    @Provides
    fun provideConvertFactory(): MoshiConverterFactory {
      return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(moshiConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): OrdersApi {
        return retrofit.create(OrdersApi::class.java)
    }


}