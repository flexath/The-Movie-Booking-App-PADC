package com.flexath.themoviebookingapp.data.vos.test

import com.google.gson.annotations.SerializedName

data class VideoResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("results")
    val results: List<VideoVO>?
)