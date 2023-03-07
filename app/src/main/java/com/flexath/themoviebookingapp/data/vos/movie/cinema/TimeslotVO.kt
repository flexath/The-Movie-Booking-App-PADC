package com.flexath.themoviebookingapp.data.vos.movie.cinema

import com.google.gson.annotations.SerializedName

data class TimeslotVO(

    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: Int?,

    @SerializedName("start_time")
    val start_time: String?,

    @SerializedName("status")
    val status: Int?
)