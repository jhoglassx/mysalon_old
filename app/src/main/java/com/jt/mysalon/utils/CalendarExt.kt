package com.jt.mysalon.utils

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

fun Calendar.parseLocalDateToString(): String {
    val date = this.time
    val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    return localDate.format(formatter)
}