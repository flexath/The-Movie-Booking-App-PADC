package com.flexath.themoviebookingapp.data.vos.movie.confirmation

import com.google.gson.annotations.SerializedName

data class CheckoutBody(

    @SerializedName("movie_id")
    val movieId: Int?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: Int?,

    @SerializedName("seat_number")
    val seatNumber: String?,

    @SerializedName("snacks")
    val snacks: MutableList<CheckoutBodySnack>?,

    @SerializedName("payment_type_id")
    val paymentTypeId: Int?,
)