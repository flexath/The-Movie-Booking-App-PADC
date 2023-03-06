package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieHomeMovieListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<MovieVO>?,

    @SerializedName("message")
    val message: String?
)