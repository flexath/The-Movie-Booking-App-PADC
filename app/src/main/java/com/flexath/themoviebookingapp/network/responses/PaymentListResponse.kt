package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.movie.PaymentVO
import com.google.gson.annotations.SerializedName

data class PaymentListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<PaymentVO>?,

    @SerializedName("message")
    val message: String?
)