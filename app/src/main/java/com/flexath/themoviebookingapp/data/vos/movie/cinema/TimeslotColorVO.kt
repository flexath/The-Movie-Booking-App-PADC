package com.flexath.themoviebookingapp.data.vos.movie.cinema

import com.google.gson.annotations.SerializedName

data class TimeslotColorVO(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("color")
    val color: String?
)
