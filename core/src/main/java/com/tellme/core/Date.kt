package com.tellme.core

import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toHumanString(): String {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.time = this

    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)

    month++

    return "${if (day < 10) "0$day" else "$day" }.${if (month < 10) "0$month" else "$month" }.${year}"
}