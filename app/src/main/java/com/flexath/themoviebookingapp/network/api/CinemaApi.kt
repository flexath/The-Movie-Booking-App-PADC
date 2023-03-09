package com.flexath.themoviebookingapp.network.api

import com.flexath.themoviebookingapp.network.responses.SeatingPlanResponse
import com.flexath.themoviebookingapp.network.responses.*
import com.flexath.themoviebookingapp.network.utils.*
import retrofit2.Call
import retrofit2.http.*

interface CinemaApi {
    // Location Screen
    @GET(API_GET_CITIES)
    fun getCities(): Call<CitiesListResponse>

    // Login Screen
    @FormUrlEncoded
    @POST(API_POST_OTP)
    fun sendOTP(@Field("phone") phone:String): Call <OTPResponse>

    // Otp Screen
    @FormUrlEncoded
    @POST(API_POST_SIGN_IN_WITH_OTP)
    fun signInWithPhoneNumber(
        @Field("phone") phone:String,
        @Field("otp") otp:String
    ): Call <OTPResponse>

    // Movie Home Screen - Banner
    @GET(API_GET_BANNERS)
    fun getBanners():Call<MovieHomeBannerResponse>

    // Movie Home Screen - Now Showing
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_STATUS) status:String = "current"
    ) : Call<MovieHomeMovieListResponse>

    // Movie Home Screen - Coming Soon
    @GET(API_GET_COMING_SOON)
    fun getComingSoonMovies(
        @Query(PARAM_STATUS) status:String = "comingsoon"
    ) : Call<MovieHomeMovieListResponse>

    // Movie Detail Screen
    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetailsById(
        @Path("movie_id") movieId:String
    ) : Call<MovieDetailResponse>

    // Movie Cinema Screen
    @GET(API_GET_CINEMA_TIMESLOTS)
    fun getCinemaTimeSlots(
        @Header(HEADER_AUTH) authorization:String,
        @Query(PARAM_DATE) date:String
    ) : Call<CinemaListResponse>

    @GET(API_GET_CINEMA_CONFIG)
    fun getCinemaConfig() : Call<ConfigListResponse>

    // Cinema Info Screen
    @GET(API_GET_CINEMA_INFO)
    fun getCinemaInfo(
        @Query(PARAM_LATEST_TIME) latestTime:String = "2022-09-17 00:23:04"
    ) : Call<CinemaInfoResponse>

    // Movie Seat Screen
    @GET(API_GET_SEAT_PLAN)
    fun getSeatPlan(
        @Header(HEADER_AUTH) authorization:String,
        @Query(PARAM_DAY_TIME_SLOT_ID) dayTimeSlot:Int,
        @Query(PARAM_BOOKING_DATE) bookingDate:String
    ) : Call<SeatingPlanResponse>
}