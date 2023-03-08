package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.CinemaInfoVO
import com.google.gson.annotations.SerializedName

data class CinemaInfoResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<CinemaInfoVO>?,

    @SerializedName("latest_time")
    val latest_time: String?,

    @SerializedName("message")
    val message: String?
)