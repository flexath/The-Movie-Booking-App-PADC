package com.flexath.themoviebookingapp.data.vos.signin

import com.google.gson.annotations.SerializedName

data class OtpVO(

    @SerializedName("email")
    val email: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("profile_image")
    val profile_image: String?,

    @SerializedName("total_expense")
    val total_expense: Int?
)