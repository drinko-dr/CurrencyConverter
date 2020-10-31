package com.guide.currencyconverter.InterfaceServices
import com.guide.currencyconverter.models.Currency
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("latest")
    fun getCurrencyList(@Query("base") base:String): Call<Currency>
}