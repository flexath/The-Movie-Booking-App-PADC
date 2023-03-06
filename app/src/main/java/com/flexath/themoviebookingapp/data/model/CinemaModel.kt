package com.flexath.themoviebookingapp.data.model

import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
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

    fun getOtp():OTPResponse?

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
}