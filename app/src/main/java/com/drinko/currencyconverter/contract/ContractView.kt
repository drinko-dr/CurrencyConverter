package com.drinko.currencyconverter.contract


interface ContractView {
    fun showText(message: String?)
    fun swapCurrency()
    fun default()
    fun getLeftCurrency(): String
    fun getRightCurrency(): String
    fun getCountCurrency(): String
    fun showError()
    fun isConnectedToNetwork()
    fun refreshCache(dateModif: String)
}