package com.flexath.themoviebookingapp.data.vos.movie

import com.google.gson.annotations.SerializedName

data class SeatVO(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("price")
    val price: Int?,

    @SerializedName("seat_name")
    val seatName: String?,

    @SerializedName("symbol")
    val symbol: String?,

    @SerializedName("type")
    val type: String?,

    var isSelected:Boolean = false
)