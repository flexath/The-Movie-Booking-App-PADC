package com.flexath.themoviebookingapp.ui.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class TimeSlotUtil {

    var dateList = mutableListOf<TimeSlot>()
    var dateListTimeSLot = mutableListOf<String>()

    private lateinit var calendar:Calendar

    @SuppressLint("SimpleDateFormat")
    fun addTimeSlotToDateList() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        calendar = Calendar.getInstance()

        var dayOfWeekVarOne = 0
        var monthVarOne = 0

        for (i in 1..14) {
            dayOfWeekVarOne = calendar.get(Calendar.DAY_OF_WEEK)
            val dayOfWeek = setUpDayOfWeek(dayOfWeekVarOne)

            monthVarOne = calendar.get(Calendar.MONTH)
            val month = setUpMonth(monthVarOne)

            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val latestTime = getLatestTime()
            dateList.add(TimeSlot(dayOfWeek, month, dayOfMonth.toString(), latestTime))
            calendar.add(Calendar.DATE, 1)
        }

        dateList.forEach { it ->
            val date = calendar.apply {
                set(Calendar.DAY_OF_WEEK, dayOfWeekVarOne)
                set(Calendar.MONTH, monthVarOne)
                set(Calendar.DAY_OF_MONTH, it.dayOfMonth.toInt())
            }.time
            val dateString = dateFormat.format(date)
            dateListTimeSLot.add(dateString)
        }

    }


    fun clearDateList() {
        dateList.clear()
    }

    private fun setUpDayOfWeek(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            1 -> "SUN"
            2 -> "MON"
            3 -> "TUE"
            4 -> "WED"
            5 -> "THU"
            6 -> "FRI"
            else -> "SAT"
        }
    }

    private fun setUpMonth(month: Int): String {
        return when (month) {
            0 -> "Jan"
            1 -> "Feb"
            2 -> "Mar"
            3 -> "Apr"
            4 -> "May"
            5 -> "Jun"
            6 -> "Jul"
            7 -> "Aug"
            8 -> "Sep"
            9 -> "Oct"
            10 -> "Nov"
            else -> "Dec"
        }
    }

    private fun getLatestTime(): String {
        val currentTime = LocalTime.now()
        return "${currentTime.hour}:${currentTime.minute}:${currentTime.second}"
    }
}