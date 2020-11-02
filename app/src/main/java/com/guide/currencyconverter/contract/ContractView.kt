package com.guide.currencyconverter.contract

import okhttp3.OkHttpClient

interface ContractView {
    fun showText(message: String?)
    fun swapCurrency()
    fun default()
    fun getLeftCurrency(): String
    fun getRightCurrency(): String
    fun getCountCurrency(): String
    fun showError()
    fun isConnectedToNetwork()
}