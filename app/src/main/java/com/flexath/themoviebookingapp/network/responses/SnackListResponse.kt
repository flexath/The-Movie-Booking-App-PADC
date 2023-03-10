package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.test.SnackVO
import com.google.gson.annotations.SerializedName

data class SnackListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<SnackVO>?,

    @SerializedName("message")
    val message: String?
)