package com.flexath.themoviebookingapp.network.responses

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message: String?
)