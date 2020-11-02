package com.guide.currencyconverter.Retrofit

import android.net.http.HttpResponseCache
import android.util.Log
import com.guide.currencyconverter.Common.Variables
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


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