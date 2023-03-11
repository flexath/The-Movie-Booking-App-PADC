package com.flexath.themoviebookingapp.data.vos.test

import com.google.gson.annotations.SerializedName

data class PaymentListResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<PaymentVO>?,

    @SerializedName("message")
    val message: String?
)