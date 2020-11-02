package com.guide.currencyconverter.presenters

import android.util.Log
import com.guide.currencyconverter.Common.Variables
import com.guide.currencyconverter.contract.ContractModel
import com.guide.currencyconverter.contract.ContractPresenter
import com.guide.currencyconverter.contract.ContractView
import com.guide.currencyconverter.models.MainModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainPresenter(_mView: ContractView) : ContractPresenter  {

    private var mView: ContractView? = null
    private var mModel: ContractModel? = null

    private var result: String? = null
    private var currencyFrom: String? = null
    private var currencyTo: String? = null
    private var countCurrency: String? = null

    init {
        this.mView = _mView
        this.mModel = MainModel(this)
        default()
    }

    override fun default() {
        mView?.default()
        mView?.refreshCache(getLastModDate())
        this.currencyFrom = this.mView?.getLeftCurrency()
        this.currencyTo = this.mView?.getRightCurrency()
        this.countCurrency = this.mView?.getCountCurrency()
    }

    override fun getCurrencyResult(){
        mView?.isConnectedToNetwork()
        mModel?.loadMessage(this.currencyFrom, this.currencyTo,this.countCurrency)
    }

    override fun onChangeCurrencyFrom(isSwap: Boolean) {
        this.currencyFrom = mView?.getLeftCurrency()
        if (!isSwap)
            getCurrencyResult()

    }

    override fun onChangeCurrencyTo() {
        this.currencyTo = mView?.getRightCurrency()
        getCurrencyResult()

    }

    override fun onDestroy() {

    }

    override fun swapCurrency() {
        mView?.swapCurrency()
    }

    override fun showResult(result: String){
        mView?.showText(result)
    }

    override fun refresh() {
        Variables.isRefresh = true
        getCurrencyResult()
        mView?.refreshCache(getLastModDate())
    }

    fun getLastModDate():String{
        val file = File(System.getProperty("java.io.tmpdir"),"http_cache")
        val lastModDate = SimpleDateFormat("dd/MM/yyyy/hh:mm")

        return lastModDate.format(file.lastModified())
    }

    override fun showError() {
        mView?.showError()
    }

}