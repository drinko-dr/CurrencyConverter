package com.drinko.currencyconverter.presenters

import com.drinko.currencyconverter.Common.Variables
import com.drinko.currencyconverter.contract.ContractModel
import com.drinko.currencyconverter.contract.ContractPresenter
import com.drinko.currencyconverter.contract.ContractView
import com.drinko.currencyconverter.models.MainModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainPresenter(_mView: ContractView) : ContractPresenter  {

    private var mView: ContractView? = null
    private var mModel: ContractModel? = null

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
        this.currencyFrom = this.mView?.getLeftCurrency()
        this.currencyTo = this.mView?.getRightCurrency()
        this.countCurrency = this.mView?.getCountCurrency()
        mView?.refreshCache(getLastModDate())
    }

    override fun getCurrencyResult(){
        this.countCurrency = this.mView?.getCountCurrency()
        mView?.isConnectedToNetwork()
        mModel?.loadMessage(this.currencyFrom, this.currencyTo)
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


    override fun swapCurrency() {
        mView?.swapCurrency()
    }

    fun showResult(result: String){
        mView?.showText(result)
    }

    override fun calcResult(msg: Double?){
        if ( !countCurrency!!.isEmpty() && msg != null ){
            val result = countCurrency!!.toDouble() * msg.toDouble()
            showResult(String.format("%.3f", result))
        }
    }

    override fun refresh() {
        Variables.isRefresh = true
        getCurrencyResult()
        mView?.refreshCache(getLastModDate())
    }

    fun getLastModDate():String{
        val file = File(System.getProperty("java.io.tmpdir"),"http_cache")
        val lastModDate = SimpleDateFormat("d MMM yyyy г. - HH:mm", Locale("ru"))

        if (file.length().toInt() == 0){
            return lastModDate.format(Date())
        }
        return lastModDate.format(file.lastModified())
    }

    override fun showError() {
        mView?.showError()
    }

}