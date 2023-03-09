package com.flexath.themoviebookingapp.network.dataagents

import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.CinemaInfoVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.network.responses.SeatingPlanResponse
import com.flexath.themoviebookingapp.network.api.CinemaApi
import com.flexath.themoviebookingapp.network.responses.*
import com.flexath.themoviebookingapp.network.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : CinemaDataAgent {

    private var mCinemaApi: CinemaApi? = null

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

        mCinemaApi = retrofit.create(CinemaApi::class.java)
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
        mCinemaApi?.signInWithPhoneNumber(phone,otp)
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

    override fun getCinemaTimeSlots(
        authorization: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getCinemaTimeSlots(authorization,date)
            ?.enqueue(object : Callback<CinemaListResponse>{
                override fun onResponse(
                    call: Call<CinemaListResponse>,
                    response: Response<CinemaListResponse>
                ) {
                    if (response.isSuccessful) {
                        val cinemaList = response.body()?.data ?: listOf()
                        onSuccess(cinemaList)
                    } else {
                        onFailure("Don't make errors,Aung Thiha")
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
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getSeatPlan(authorization,dayTimeSlotId,bookingDate)
            ?.enqueue(object : Callback<SeatingPlanResponse>{
                override fun onResponse(
                    call: Call<SeatingPlanResponse>,
                    response: Response<SeatingPlanResponse>
                ) {
                    if (response.isSuccessful) {
                        val seatList = response.body()?.data ?: listOf()
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
}