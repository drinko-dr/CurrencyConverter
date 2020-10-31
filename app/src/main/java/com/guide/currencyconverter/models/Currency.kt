package com.guide.currencyconverter.models

import android.util.ArrayMap

data class Currency (
    var rates: ArrayMap<String, String>? = null,
    var base: String? = null,
    var date: String? = null
)