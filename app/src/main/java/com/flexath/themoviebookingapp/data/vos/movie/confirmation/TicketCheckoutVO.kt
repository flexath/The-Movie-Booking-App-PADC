package com.flexath.themoviebookingapp.data.vos.movie.confirmation

import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotVO
import com.google.gson.annotations.SerializedName

data class TicketCheckoutVO(

    @SerializedName("id")
    val id:Int?,

    @SerializedName("booking_no")
    val bookingNo:String?,

    @SerializedName("booking_date")
    val bookingDate:String?,

    @SerializedName("row")
    val row:String?,

    @SerializedName("seat")
    val seat:String?,

    @SerializedName("total_seat")
    val totalSeat:Int?,

    @SerializedName("total")
    val total:String?,

    @SerializedName("movie_id")
    val movieId:Int?,

    @SerializedName("cinema_id")
    val cinemaId:Int?,

    @SerializedName("username")
    val username:String?,

    @SerializedName("timeslot")
    val timeslot:TimeslotVO?,

    @SerializedName("snacks")
    val snacks:List<TicketCheckoutSnackVO>?,

    @SerializedName("qr_code")
    val qrCode:String?
) : java.io.Serializable
