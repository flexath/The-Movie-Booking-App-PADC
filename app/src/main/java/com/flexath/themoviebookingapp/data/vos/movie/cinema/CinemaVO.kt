package com.flexath.themoviebookingapp.data.vos.movie.cinema

import com.google.gson.annotations.SerializedName

data class CinemaVO(

    @SerializedName("cinema")
    val cinema: String?,

    @SerializedName("cinema_id")
    val cinemaId: Int?,

    @SerializedName("timeslots")
    val timeslots: List<TimeslotVO>?
)