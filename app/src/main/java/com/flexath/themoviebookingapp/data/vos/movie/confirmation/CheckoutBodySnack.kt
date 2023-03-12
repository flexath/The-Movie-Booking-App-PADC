package com.flexath.themoviebookingapp.data.vos.movie.confirmation

import com.google.gson.annotations.SerializedName

data class CheckoutBodySnack(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("quantity")
    val quantity: Int?
)