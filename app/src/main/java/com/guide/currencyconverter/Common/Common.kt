package com.guide.currencyconverter.Common

import com.guide.currencyconverter.InterfaceServices.RetrofitServices
import com.guide.currencyconverter.Retrofit.RetrofitClient
import okhttp3.Cache
import okhttp3.OkHttpClient

object Common{
    private val BASE_URL = "https://api.exchangeratesapi.io/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}