package com.guide.currencyconverter.presenters

import android.util.Log
import com.guide.currencyconverter.contract.ContractModel
import com.guide.currencyconverter.contract.ContractPresenter
import com.guide.currencyconverter.contract.ContractView
import com.guide.currencyconverter.models.MainModel

class MainPresenter(_mView: ContractView) : ContractPresenter  {
    private val TAG = "MainPresenter"

    private var mView: ContractView? = null
    private var mModel: ContractModel? = null

    private var message: String? = null

    init {
        this.mView = _mView
        this.mModel = MainModel()
        Log.d(TAG, "Constructor")
    }

    override fun onButtonWasClicked() {
        message = mModel?.loadMessage()
        mView?.showText(message)
        Log.d(TAG, "onButtonWasClicked()")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
    }
}