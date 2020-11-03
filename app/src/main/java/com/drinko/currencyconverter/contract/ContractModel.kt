package com.drinko.currencyconverter.contract

interface ContractModel {
    fun loadMessage(
        currencyFrom: String?,
        currencyTo: String?
    )
}