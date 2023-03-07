package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.google.gson.annotations.SerializedName

data class ConfigListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<ConfigVO>?,

    @SerializedName("message")
    val message: String?
)