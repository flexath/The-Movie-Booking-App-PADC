package com.flexath.themoviebookingapp.data.vos.movie.confirmation

import com.google.gson.annotations.SerializedName

data class TicketCheckoutResponse(

    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String,

    @SerializedName("data")
    val data:TicketCheckoutVO?
)
