package com.flexath.themoviebookingapp.data.vos.movie.confirmation

import com.google.gson.annotations.SerializedName

data class TicketCheckoutSnackVO(

    @SerializedName("description")
    val description: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("price")
    val price: Int?,

    @SerializedName("unit_price")
    val unitPrice: Int?,

    @SerializedName("total_price")
    val totalPrice: Int?,

    @SerializedName("quantity")
    val quantity: Int?
)
