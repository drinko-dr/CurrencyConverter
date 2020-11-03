package com.drinko.currencyconverter.views

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.drinko.currencyconverter.R
import com.drinko.currencyconverter.Common.Variables
import com.drinko.currencyconverter.contract.ContractPresenter
import com.drinko.currencyconverter.contract.ContractView
import com.drinko.currencyconverter.presenters.MainPresenter


class MainActivity : AppCompatActivity(), ContractView {

    private var mPresenter: ContractPresenter? = null
    private var isSwap: Boolean = true

    private var mSwapBtn: ImageButton? = null
    private var mRefreshBtn: ImageButton? = null

    private var mDateTextView: TextView? = null
    private var mLeftTextView: EditText? = null
    private var mRightTextView: TextView? = null

    private var leftSpinner: Spinner? = null
    private var rightSpinner: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLeftTextView = findViewById(R.id.left_textbox_view)
        mRightTextView = findViewById(R.id.right_textbox_view)
        mSwapBtn = findViewById(R.id.swap_btn_view)
        mDateTextView = findViewById(R.id.dateRefresh_text_view)
        mRefreshBtn = findViewById(R.id.refresh_cache_btn_view)

        leftSpinner = findViewById(R.id.left_spinner_view)
        rightSpinner= findViewById(R.id.right_spinner_view)

        mPresenter = MainPresenter(this)




        leftSpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mPresenter!!.onChangeCurrencyFrom(isSwap)
            }

        }
        rightSpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mPresenter!!.onChangeCurrencyTo()
                isSwap = false
            }

        }


        mLeftTextView?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mPresenter!!.getCurrencyResult()
            }

        })

        mSwapBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mPresenter!!.swapCurrency()
            }

        } )


        mRefreshBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mPresenter!!.refresh()
            }

        } )



    }

    override fun swapCurrency() {
        if (leftSpinner!!.selectedItemPosition != rightSpinner!!.selectedItemPosition){
            var temp = leftSpinner!!.selectedItemPosition
            leftSpinner?.setSelection(rightSpinner!!.selectedItemPosition)
            rightSpinner?.setSelection(temp)
            isSwap = true
        }
    }

    override fun showText(message: String?) {
        mRightTextView?.setText(message)
    }


    override fun default(){
        isConnectedToNetwork()
        mLeftTextView?.setText("1")
        var currencyList = resources.getStringArray(R.array.Currency)
        if( leftSpinner != null && rightSpinner != null ){
            val adapter = ArrayAdapter(this, R.layout.spinner_row, currencyList)
            leftSpinner!!.adapter = adapter
            leftSpinner!!.setSelection(1)
            rightSpinner!!.adapter = adapter
        }
    }



    override fun getLeftCurrency(): String {
        return leftSpinner?.getSelectedItem().toString()
    }

    override fun getRightCurrency(): String{
        return rightSpinner?.getSelectedItem().toString()
    }
    override fun getCountCurrency(): String{
        return mLeftTextView?.text.toString()
    }

    override fun showError(){
        Toast.makeText(applicationContext,
            "Что то пошло не так, проверьте подключение к интернету или попытайтесь позднее", Toast.LENGTH_SHORT).show()

    }

    override fun isConnectedToNetwork(){
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        Variables.isNetworkConnected =  connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

    override fun refreshCache(dateModif: String) {
        var text = applicationContext.getString(R.string.dateRefresh)
        mDateTextView?.setText("$text $dateModif")
    }
}
