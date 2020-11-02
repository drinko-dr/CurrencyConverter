package com.guide.currencyconverter.Common

import com.guide.currencyconverter.InterfaceServices.RetrofitServices
import com.guide.currencyconverter.httpConnection.RetrofitClient

object Common{
    private val BASE_URL = "https://api.exchangeratesapi.io/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}