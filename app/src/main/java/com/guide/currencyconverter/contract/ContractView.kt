package com.guide.currencyconverter.contract

interface ContractView {
    fun showText(message: String?)
    fun swapCurrency()
    fun default()
    fun getLeftCurrency(): String
    fun getRightCurrency(): String
    fun getCountCurrency(): String
}