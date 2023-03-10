package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.google.gson.annotations.SerializedName

data class SeatingPlanResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: MutableList<MutableList<SeatVO>>?,

    @SerializedName("message")
    val message: String?
)