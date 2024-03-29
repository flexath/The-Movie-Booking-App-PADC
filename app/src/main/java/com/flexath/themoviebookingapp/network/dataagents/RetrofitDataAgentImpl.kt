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
import com.flexath.themoviebookingapp.network.responses.PaymentListResponse
import com.flexath.themoviebookingapp.data.vos.movie.PaymentVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.CheckoutBody
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutResponse
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutVO
import com.flexath.themoviebookingapp.data.vos.movie.VideoResponse
import com.flexath.themoviebookingapp.data.vos.movie.VideoVO
import com.flexath.themoviebookingapp.network.responses.SeatingPlanResponse
import com.flexath.themoviebookingapp.network.api.CinemaApi
import com.flexath.themoviebookingapp.network.responses.*
import com.flexath.themoviebookingapp.network.utils.BASE_URL
import com.flexath.themoviebookingapp.network.utils.BASE_URL_TMDB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : CinemaDataAgent {

    private var mCinemaApi: CinemaApi? = null
    private var mCinemaApiTwo: CinemaApi? = null

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val retrofitTwo = Retrofit.Builder()
            .baseUrl(BASE_URL_TMDB)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        mCinemaApi = retrofit.create(CinemaApi::class.java)
        mCinemaApiTwo = retrofitTwo.create(CinemaApi::class.java)
    }

    override fun getCities(onSuccess: (List<CitiesVO>) -> Unit, onFailure: (String) -> Unit) {
        mCinemaApi?.getCities()
            ?.enqueue(object : Callback<CitiesListResponse> {
                override fun onResponse(
                    call: Call<CitiesListResponse>,
                    response: Response<CitiesListResponse>
                ) {
                    if (response.isSuccessful) {
                        val citiesList = response.body()?.data ?: listOf()
                        onSuccess(citiesList)
                    } else {
                        onFailure("Cities response failed")
                    }
                }

                override fun onFailure(call: Call<CitiesListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun sendOTP(
        phone: String,
        onSuccess: (OTPResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.sendOTP(phone)
            ?.enqueue(object : Callback<OTPResponse> {
                override fun onResponse(call: Call<OTPResponse>, response: Response<OTPResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<OTPResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun signInWithPhoneNumber(
        phone: String,
        otp: String,
        onSuccess: (OTPResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.signInWithPhoneNumber(phone, otp)
            ?.enqueue(object : Callback<OTPResponse> {
                override fun onResponse(call: Call<OTPResponse>, response: Response<OTPResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure(response.body()?.message.toString())
                    }
                }

                override fun onFailure(call: Call<OTPResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getBanners(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {
        mCinemaApi?.getBanners()
            ?.enqueue(object : Callback<MovieHomeBannerResponse> {
                override fun onResponse(
                    call: Call<MovieHomeBannerResponse>,
                    response: Response<MovieHomeBannerResponse>
                ) {
                    if (response.isSuccessful) {
                        val citiesList = response.body()?.data ?: listOf()
                        onSuccess(citiesList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<MovieHomeBannerResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getNowPlayingMovies()
            ?.enqueue(object : Callback<MovieHomeMovieListResponse> {
                override fun onResponse(
                    call: Call<MovieHomeMovieListResponse>,
                    response: Response<MovieHomeMovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.data ?: listOf()
                        onSuccess(movieList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<MovieHomeMovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getComingSoonMovies()
            ?.enqueue(object : Callback<MovieHomeMovieListResponse> {
                override fun onResponse(
                    call: Call<MovieHomeMovieListResponse>,
                    response: Response<MovieHomeMovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.data ?: listOf()
                        onSuccess(movieList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<MovieHomeMovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getMovieDetailsById(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getMovieDetailsById(movieId)
            ?.enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        val movie = response.body()?.data
                        movie?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getMovieTrailerById(
        movieId: String,
        onSuccess: (List<VideoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApiTwo?.getMovieTrailerById(movieId)
            ?.enqueue(object : Callback<VideoResponse> {
                override fun onResponse(
                    call: Call<VideoResponse>,
                    response: Response<VideoResponse>
                ) {
                    if (response.isSuccessful) {
                        val videoList = response.body()?.results ?: listOf()
                        onSuccess(videoList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getCinemaTimeSlots(
        authorization: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getCinemaTimeSlots(authorization, date)
            ?.enqueue(object : Callback<CinemaListResponse> {
                override fun onResponse(
                    call: Call<CinemaListResponse>,
                    response: Response<CinemaListResponse>
                ) {
                    if (response.isSuccessful) {
                        val cinemaList = response.body()?.data ?: listOf()
                        onSuccess(cinemaList)
                    } else {
                        onFailure("Timeslot response error")
                    }
                }

                override fun onFailure(call: Call<CinemaListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getCinemaConfig(onSuccess: (List<ConfigVO>) -> Unit, onFailure: (String) -> Unit) {
        mCinemaApi?.getCinemaConfig()
            ?.enqueue(object : Callback<ConfigListResponse> {
                override fun onResponse(
                    call: Call<ConfigListResponse>,
                    response: Response<ConfigListResponse>
                ) {
                    if (response.isSuccessful) {
                        val configList = response.body()?.data ?: listOf()
                        onSuccess(configList)
                    } else {
                        onFailure("Cinema Config response failed")
                    }
                }

                override fun onFailure(call: Call<ConfigListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getCinemaInfo(
        onSuccess: (List<CinemaInfoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getCinemaInfo()
            ?.enqueue(object : Callback<CinemaInfoResponse> {
                override fun onResponse(
                    call: Call<CinemaInfoResponse>,
                    response: Response<CinemaInfoResponse>
                ) {
                    if (response.isSuccessful) {
                        val cinemaList = response.body()?.data ?: listOf()
                        onSuccess(cinemaList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<CinemaInfoResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getSeatPlan(
        authorization: String,
        dayTimeSlotId: Int,
        bookingDate: String,
        onSuccess: (MutableList<MutableList<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getSeatPlan(authorization, dayTimeSlotId, bookingDate)
            ?.enqueue(object : Callback<SeatingPlanResponse> {
                override fun onResponse(
                    call: Call<SeatingPlanResponse>,
                    response: Response<SeatingPlanResponse>
                ) {
                    if (response.isSuccessful) {
                        val seatList = response.body()?.data ?: mutableListOf()
                        onSuccess(seatList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<SeatingPlanResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getSnackCategory(
        authorization: String,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getSnackCategory(authorization)
            ?.enqueue(object : Callback<SnackCategoryResponse> {
                override fun onResponse(
                    call: Call<SnackCategoryResponse>,
                    response: Response<SnackCategoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val snackCategoryList = response.body()?.data ?: listOf()
                        onSuccess(snackCategoryList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<SnackCategoryResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getSnackByCategory(
        authorization: String,
        categoryId: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getSnackByCategory(authorization, categoryId)
            ?.enqueue(object : Callback<SnackListResponse> {
                override fun onResponse(
                    call: Call<SnackListResponse>,
                    response: Response<SnackListResponse>
                ) {
                    if (response.isSuccessful) {
                        val snackList = response.body()?.data ?: listOf()
                        onSuccess(snackList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<SnackListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getPaymentTypes(
        authorization: String,
        onSuccess: (List<PaymentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getPaymentTypes(authorization)
            ?.enqueue(object : Callback<PaymentListResponse> {
                override fun onResponse(
                    call: Call<PaymentListResponse>,
                    response: Response<PaymentListResponse>
                ) {
                    if (response.isSuccessful) {
                        val paymentList = response.body()?.data ?: listOf()
                        onSuccess(paymentList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<PaymentListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getTicketCheckout(
        authorization: String,
        ticketCheckout: CheckoutBody,
        onSuccess: (TicketCheckoutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getTicketCheckout(authorization, ticketCheckout)
            ?.enqueue(object : Callback<TicketCheckoutResponse> {
                override fun onResponse(
                    call: Call<TicketCheckoutResponse>,
                    response: Response<TicketCheckoutResponse>
                ) {
                    if (response.isSuccessful) {
                        val ticket = response.body()?.data
                        if (ticket != null) {
                            onSuccess(ticket)
                        }
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<TicketCheckoutResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun logout(
        authorization: String,
        onSuccess: (LogoutResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.logout(authorization)
            ?.enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }
}