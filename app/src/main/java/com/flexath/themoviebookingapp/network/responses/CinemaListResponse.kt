package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.google.gson.annotations.SerializedName

data class CinemaListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<CinemaVO>?,

    @SerializedName("message")
    val message: String?
)