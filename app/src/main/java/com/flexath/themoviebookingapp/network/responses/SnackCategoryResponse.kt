package com.flexath.themoviebookingapp.network.responses

import com.flexath.themoviebookingapp.data.vos.test.SnackCategoryVO
import com.google.gson.annotations.SerializedName

data class SnackCategoryResponse(

    @SerializedName("code")
    val code: Int?,

    @SerializedName("data")
    val data: List<SnackCategoryVO>?,

    @SerializedName("message")
    val message: String?
)