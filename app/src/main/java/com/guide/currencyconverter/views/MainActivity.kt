package com.guide.currencyconverter.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.guide.currencyconverter.R
import com.guide.currencyconverter.contract.ContractPresenter
import com.guide.currencyconverter.contract.ContractView
import com.guide.currencyconverter.presenters.MainPresenter


class MainActivity : AppCompatActivity(), ContractView {

    private val TAG = "MainActivity"

    private var mPresenter: ContractPresenter? = null
    private var mButton: Button? = null
    private var mTextView: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this)

        mTextView = findViewById(R.id.text_view)
        mButton = findViewById(R.id.button_view)

        mButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                (mPresenter as MainPresenter).onButtonWasClicked()
            }

        } )
    }

    override fun showText(message: String?) {
        mTextView?.setText(message)
        Log.d(TAG, "showMessage()")
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
        Log.d(TAG, "onDestroy()")
    }
}
