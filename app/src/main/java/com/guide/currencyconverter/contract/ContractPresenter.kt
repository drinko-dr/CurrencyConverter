package com.guide.currencyconverter.contract

interface ContractPresenter {
    fun swapCurrency()
    fun refresh()
    fun default()
    fun getCurrencyResult()
    fun onChangeCurrencyFrom(isSwap: Boolean)
    fun onChangeCurrencyTo()
    fun calcResult(msg: Double?)
    fun showError()
}