package com.guide.currencyconverter.models

import android.content.ContentValues.TAG
import android.util.Log
import com.guide.currencyconverter.contract.ContractModel

class MainModel : ContractModel {
    override fun loadMessage(): String? {
        Log.d(TAG, "load message")
        return "some text of model"
    }
}