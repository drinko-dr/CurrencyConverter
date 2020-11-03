package com.drinko.currencyconverter.Common

import com.drinko.currencyconverter.InterfaceServices.RetrofitServices
import com.drinko.currencyconverter.httpConnection.RetrofitClient

object Common{
    private val BASE_URL = "https://api.exchangeratesapi.io/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}