package com.guide.currencyconverter.presenters

import android.util.Log
import com.guide.currencyconverter.contract.ContractModel
import com.guide.currencyconverter.contract.ContractPresenter
import com.guide.currencyconverter.contract.ContractView
import com.guide.currencyconverter.models.MainModel

class MainPresenter(_mView: ContractView) : ContractPresenter  {

    private var mView: ContractView? = null
    private var mModel: ContractModel? = null

    private var result: String? = null
    private var currencyFrom: String? = null
    private var currencyTo: String? = null
    private var countCurrency: String? = null

    init {
        this.mView = _mView
        this.mModel = MainModel()
        default()
    }

    override fun default() {
        mView?.default()
        this.currencyFrom = this.mView?.getLeftCurrency()
        this.currencyTo = this.mView?.getRightCurrency()
        this.countCurrency = this.mView?.getCountCurrency()
        getCurrencyResult()
    }

    override fun getCurrencyResult(){
        result = mModel?.loadMessage(this.currencyFrom, this.currencyTo,this.countCurrency)
        mView?.showText(result)
    }

    override fun onChangeCurrencyFrom() {
        currencyFrom = mView?.getLeftCurrency()

    }

    override fun onChangeCurrencyTo() {
        currencyFrom = mView?.getRightCurrency()
    }

    override fun onDestroy() {
    }

    override fun swapCurrency() {
        mView?.swapCurrency()
        getCurrencyResult()
    }

    override fun refresh() {
    }

}