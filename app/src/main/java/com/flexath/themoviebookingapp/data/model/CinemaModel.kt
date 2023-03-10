package com.flexath.themoviebookingapp.data.model

import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.CinemaInfoVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.data.vos.test.SnackCategoryVO
import com.flexath.themoviebookingapp.data.vos.test.SnackVO
import com.flexath.themoviebookingapp.network.responses.OTPResponse

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

    fun getCinemaConfig():List<ConfigVO>?

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
}