package com.guide.currencyconverter.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.guide.currencyconverter.R
import com.guide.currencyconverter.contract.ContractPresenter
import com.guide.currencyconverter.contract.ContractView
import com.guide.currencyconverter.presenters.MainPresenter
import java.lang.reflect.Array


class MainActivity : AppCompatActivity(), ContractView {

    private var mPresenter: ContractPresenter? = null
    private var mSwapBtn: ImageButton? = null
    private var mRefreshBtn: Button? = null
    private var mLeftTextView: EditText? = null
    private var mRightTextView: EditText? = null
    private var leftSpinner: Spinner? = null
    private var rightSpinner: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLeftTextView = findViewById(R.id.left_textbox_view)
        mRightTextView = findViewById(R.id.right_textbox_view)
        mSwapBtn = findViewById(R.id.swap_btn_view)
//        mRefreshBtn = findViewById(R.id.button_view)

        leftSpinner = findViewById(R.id.left_spinner_view)
        rightSpinner= findViewById(R.id.right_spinner_view)

        mPresenter = MainPresenter(this)

        leftSpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mPresenter!!.onChangeCurrencyFrom()
            }

        }
        rightSpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mPresenter!!.onChangeCurrencyTo()
            }

        }


        mLeftTextView?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mPresenter!!.getCurrencyResult()
            }

        })

        mSwapBtn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mPresenter!!.swapCurrency()
            }

        } )

    }

    override fun swapCurrency() {
        var temp = leftSpinner!!.selectedItemPosition
        leftSpinner?.setSelection(rightSpinner!!.selectedItemPosition)
        rightSpinner?.setSelection(temp)

    }

    override fun showText(message: String?) {
        mRightTextView?.setText(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

    override fun default(){
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
}
