package com.guide.currencyconverter.models

data class Currency(
    val base: String,
    val date: String,
    val rates: Rates
)

data class Rates(
    val EUR: Double,
    val RUB: Double,
    val USD: Double
)