package com.guide.currencyconverter.models

import android.util.Log
import com.guide.currencyconverter.Common.Common
import com.guide.currencyconverter.InterfaceServices.RetrofitServices
import com.guide.currencyconverter.Common.Variables
import com.guide.currencyconverter.contract.ContractModel
import com.guide.currencyconverter.contract.ContractPresenter
import retrofit2.Call
import retrofit2.Response


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
        getCurrencyList(currencyFrom.toString(), currencyTo.toString())
        
    }


    private fun getCurrencyList(from:String, to: String){
        Log.i("model getCurrencyList()", from)
        Log.i("check inet", Variables.isNetworkConnected.toString())

        mServices?.getCurrencyList(from)?.enqueue(object: retrofit2.Callback<Currency> {
            override fun onFailure(call: Call<Currency>, t: Throwable) {
                Log.i("model", "fail")
                Log.i("model", t.message)
                mPresenter?.showError()
            }

            override fun onResponse(
                call: Call<Currency>,
                response: Response<Currency>
            ) {

                if (response.isSuccessful){
                    Log.i("model response", (response.body() as Currency).rates.get(to).toString())
                    mPresenter?.showResult(String.format("%.4f", (response.body() as Currency)
                        .rates
                        .get(to) )
                    )
                    mPresenter?.showError()

                }

            }

        })
    }





    override fun defautl(){

    }
}
