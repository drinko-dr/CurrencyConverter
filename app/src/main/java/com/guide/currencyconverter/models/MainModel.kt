package com.guide.currencyconverter.models

import android.util.Log
import com.guide.currencyconverter.contract.ContractModel



class MainModel : ContractModel {

    private val BASE_URL = "https://api.exchangeratesapi.io/"

    override fun loadMessage(
        currencyFrom: String?,
        currencyTo: String?,
        countCurrency: String?
    ): String? {



        return getCurrentCourse(currencyFrom.toString(), currencyTo.toString()).toString()
    }

    fun getCurrentCourse(base: String, to: String): String?{
        var error: String? = null
        var course: String? = null


    }


    override fun defautl(){

    }
}
