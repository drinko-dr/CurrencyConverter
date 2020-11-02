package com.guide.currencyconverter.contract

interface ContractPresenter {
    fun onDestroy()
    fun swapCurrency()
    fun refresh()
    fun default()
    fun getCurrencyResult()
    fun onChangeCurrencyFrom(isSwap: Boolean)
    fun onChangeCurrencyTo()
    fun showResult(result: String)
    fun showError()
}