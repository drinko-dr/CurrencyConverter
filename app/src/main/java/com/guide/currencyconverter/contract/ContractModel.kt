package com.guide.currencyconverter.contract

interface ContractModel {
    fun loadMessage(
        currencyFrom: String?,
        currencyTo: String?
    )
}