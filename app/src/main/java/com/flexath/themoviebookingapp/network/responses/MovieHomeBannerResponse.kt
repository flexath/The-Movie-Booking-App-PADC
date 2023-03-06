package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.google.gson.annotations.SerializedName

data class MovieHomeBannerResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<BannerVO>?,

    @SerializedName("message")
    val message: String?
)