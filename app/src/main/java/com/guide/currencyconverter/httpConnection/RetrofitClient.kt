package com.guide.currencyconverter.httpConnection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//ConnectivityManager.NetworkCallback().onUnavailable()
object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(CacheOkHttpClient.client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }


}