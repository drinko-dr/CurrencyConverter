package com.guide.currencyconverter.models

import android.util.Log
import com.guide.currencyconverter.Common.Common
import com.guide.currencyconverter.InterfaceServices.RetrofitServices
import com.guide.currencyconverter.Retrofit.RetrofitClient
import com.guide.currencyconverter.contract.ContractModel
import com.guide.currencyconverter.contract.ContractPresenter
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MainModel(_mPresenter: ContractPresenter) : ContractModel {

    private var mServices: RetrofitServices? = null
    private var mPresenter: ContractPresenter? = null

    init {
        this.mPresenter = _mPresenter
    }

    override fun loadMessage(
        currencyFrom: String?,
        currencyTo: String?,
        countCurrency: String?
    ){

         mServices = Common.retrofitService
        getCurrencyList(currencyFrom.toString())
    }


    fun getCurrencyList(from:String){
        mServices?.getCurrencyList(from)?.enqueue(object: retrofit2.Callback<Currency> {
            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Log.i("model", "fail")
                Log.i("model", t.message)
            }

            override fun onResponse(
                call: Call<Currency>,
                response: Response<Currency>
            ) {
                Log.i("model", "text")

                Log.i("model", (response.body() as Currency).rates.EUR.toString())
                mPresenter?.showResult(response.message())
            }

        })
    }




    override fun defautl(){

    }
}
