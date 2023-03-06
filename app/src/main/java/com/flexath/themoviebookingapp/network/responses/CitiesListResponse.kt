package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.google.gson.annotations.SerializedName

data class CitiesListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<CitiesVO>?,

    @SerializedName("message")
    val message: String?
)