package com.jt.mysalon.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formatCurrency(): String {
    val currency = NumberFormat.getCurrencyInstance(Locale.getDefault())

    return currency.format(this)
}