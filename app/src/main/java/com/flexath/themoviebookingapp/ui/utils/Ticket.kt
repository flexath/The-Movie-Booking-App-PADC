package com.flexath.themoviebookingapp.ui.utils

import com.flexath.themoviebookingapp.data.vos.movie.SnackVO

data class Ticket(
    val movieId:String?,                             // Movie Detail Screen
    val movieName:String?,                           // Movie Detail Screen
    val cinemaInfo:CinemaData?,                      // Movie Cinema Screen
    val seatInfo:SeatData?,                          // Movie Seat Screen
    val snackTotalPrice:Long?,                       // Movie Snack Screen
    var snackList:List<SnackVO>?                     // Movie Snack Screen
): java.io.Serializable
