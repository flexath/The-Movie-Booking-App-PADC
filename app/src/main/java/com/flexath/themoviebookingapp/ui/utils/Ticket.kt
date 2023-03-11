package com.flexath.themoviebookingapp.ui.utils

import com.flexath.themoviebookingapp.data.vos.movie.SnackVO

data class Ticket(
    val movieName:String?,                          // Movie Detail Screen
    val cinemaInfo:CinemaData?,                      // Movie Cinema Screen
    val seatInfo:SeatData?,                          // Movie Seat Screen
    val snackTotalPrice:Long?,                      // Movie Snack Screen
    val snackList:List<SnackVO>?           // Movie Snack Screen
): java.io.Serializable
