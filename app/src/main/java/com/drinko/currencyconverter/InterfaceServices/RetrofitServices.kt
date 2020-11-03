package com.drinko.currencyconverter.InterfaceServices
import com.drinko.currencyconverter.models.Currency
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("latest")
    fun getCurrencyList(@Query("base") base:String): Call<Currency>
}