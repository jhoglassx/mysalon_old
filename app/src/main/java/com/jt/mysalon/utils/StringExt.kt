package com.jt.mysalon.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.parseCalendar(): Calendar {
    val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val dateObj: Date? = format.parse(this)
    val calendar: Calendar = Calendar.getInstance()
    if (dateObj != null) {
        calendar.time = dateObj
    }
    return calendar
}

fun String.fistLetterUpperCase(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}