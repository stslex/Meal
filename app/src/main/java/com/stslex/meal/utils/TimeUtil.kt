package com.stslex.meal.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object TimeUtil {

    private const val DATE_FORMAT = "dd.MM.yyyy"
    private const val TIME_FORMAT = "kk:mm"
    private const val MINUTE_FORMAT = "mm"
    private const val HOUR_FORMAT = "kk"

    fun Date.convertTime(): String = convert(TIME_FORMAT, this)

    fun Date.convertHour(): Int = convert(HOUR_FORMAT, this).toInt()

    fun Date.convertMinute(): Int = convert(MINUTE_FORMAT, this).toInt()

    fun Date.convertDate(): String = convert(DATE_FORMAT, this)

    private fun convert(format: String, time: Date): String =
        SimpleDateFormat(format, Locale.getDefault()).format(time)

    fun getLocalToUTCDate(date: Date?): String? {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        val time = calendar.time
        @SuppressLint("SimpleDateFormat") val outputFmt =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        outputFmt.timeZone = TimeZone.getTimeZone("UTC")
        return outputFmt.format(time)
    }
}