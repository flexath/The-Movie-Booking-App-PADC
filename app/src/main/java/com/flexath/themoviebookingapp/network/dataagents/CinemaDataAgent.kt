package com.flexath.themoviebookingapp.network.dataagents

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
import com.flexath.themoviebookingapp.network.responses.LogoutResponse
import com.flexath.themoviebookingapp.network.responses.OTPResponse

interface CinemaDataAgent {

    // Location Screen
    fun getCities(
        onSuccess:(List<CitiesVO>) -> Unit,
        onFailure:(String) -> Unit
    )

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

    // Movie Cinema Screen
    fun getCinemaTimeSlots(
        authorization:String,
        date:String,
        onSuccess:(List<CinemaVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getCinemaConfig(
        onSuccess:(List<ConfigVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    // Cinema Info Screen
    fun getCinemaInfo(
        onSuccess:(List<CinemaInfoVO>) -> Unit,
        onFailure:(String) -> Unit
    )

    // Movie Cinema Screen
    fun getSeatPlan(
        authorization:String,
        dayTimeSlotId:Int,
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
}