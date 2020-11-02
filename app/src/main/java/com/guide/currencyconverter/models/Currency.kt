package com.guide.currencyconverter.models

import android.util.ArrayMap

data class Currency(
    val base: String,
    val date: String,
    val rates: ArrayMap<String, Double>
)
