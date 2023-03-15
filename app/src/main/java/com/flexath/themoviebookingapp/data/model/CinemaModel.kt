package com.flexath.themoviebookingapp.data.model

import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.CinemaInfoVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.data.vos.movie.SnackCategoryVO
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.data.vos.movie.PaymentVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.CheckoutBody
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutVO
import com.flexath.themoviebookingapp.data.vos.movie.VideoVO
import com.flexath.themoviebookingapp.data.vos.ticket.TicketInformation
import com.flexath.themoviebookingapp.network.responses.LogoutResponse
import com.flexath.themoviebookingapp.network.responses.OTPResponse
import com.flexath.themoviebookingapp.ui.utils.Ticket

interface CinemaModel {

    // Location Screen
    fun insertCities(
        onSuccess:(List<CitiesVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getCities():List<CitiesVO>?

    // Login Screen
    fun sendOTP(
        phone:String,
        onSuccess:(OTPResponse) -> Unit,
        onFailure:(String) -> Unit
    )

    // Otp Screen
    fun signInWithPhoneNumber(
        phone:String,
        otp:String,
        onSuccess:(OTPResponse) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getOtp(code:Int):OTPResponse?

    // Movie Home Screen - Banner
    fun getBanners(
        onSuccess:(List<BannerVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    // Movie Home Screen - Now Showing or Coming Soon
    fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getComingSoonMovies(
        onSuccess:(List<MovieVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    // Movie Detail Screen
    fun getMovieDetailsById(
        movieId:String,
        onSuccess:(MovieVO) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getMovieTrailerById(
        movieId:String,
        onSuccess:(List<VideoVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getMovieByIdForTicket(
        movieId:String
    ):MovieVO?

    // Movie Cinema Screen
    fun getCinemaTimeSlots(
        authorization:String,
        date:String,
        onSuccess:(List<CinemaVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun insertCinemaConfig(
        onSuccess:(List<ConfigVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getCinemaConfig(key:String):ConfigVO?

    // Cinema Info Screen
    fun insertCinemaInfo(
        onSuccess:(List<CinemaInfoVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getCinemaInfo(cinemaId:Int):CinemaInfoVO?

    // Movie Cinema Screen
    fun getSeatPlan(
        authorization:String,
        dayTimeSlotId: Int,
        bookingDate:String,
        onSuccess:(MutableList<MutableList<SeatVO>>) -> Unit,
        onFailure:(String) -> Unit
    )

    // Movie Snack Screen
    fun getSnackCategory(
        authorization:String,
        onSuccess:(List<SnackCategoryVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getSnackByCategory(
        authorization:String,
        categoryId:String,
        onSuccess:(List<SnackVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getSnackList():MutableList<SnackVO>

    // Movie Payment Screen
    fun getPaymentTypes(
        authorization:String,
        onSuccess:(List<PaymentVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    // Movie Confirmation Screen but it called in Payment Screen
    fun getTicketCheckout(
        authorization:String,
        ticketCheckout: CheckoutBody,
        onSuccess:(TicketCheckoutVO) -> Unit,
        onFailure:(String) -> Unit
    )

    // Profile Tab Screen - Logout
    fun logout(
        authorization:String,
        onSuccess:(LogoutResponse) -> Unit,
        onFailure:(String) -> Unit
    )
    fun deleteAllEntities()

    // Ticket Tab Screen
    fun insertTicket(ticket:TicketInformation)

    fun getAllTickets():List<TicketInformation>?

    fun deleteTicket(ticketId:Int)
}