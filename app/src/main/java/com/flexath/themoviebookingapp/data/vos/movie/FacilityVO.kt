package com.flexath.themoviebookingapp.data.vos.movie

import com.google.gson.annotations.SerializedName

data class FacilityVO(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("img")
    val img: String?,

    @SerializedName("title")
    val title: String?
)