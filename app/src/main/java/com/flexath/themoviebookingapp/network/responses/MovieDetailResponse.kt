package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: MovieVO?,

    @SerializedName("message")
    val message: String?
)