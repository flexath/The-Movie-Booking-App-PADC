package com.flexath.themoviebookingapp.data.model

import android.content.Context
import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.COMING_SOON_MOVIE
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.NOW_PLAYING_MOVIE
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.network.dataagents.CinemaDataAgent
import com.flexath.themoviebookingapp.network.dataagents.RetrofitDataAgentImpl
import com.flexath.themoviebookingapp.network.responses.OTPResponse
import com.flexath.themoviebookingapp.persistence.CinemaRoomDatabase

object CinemaModelImpl : CinemaModel {

    private val mMovieDataAgent: CinemaDataAgent = RetrofitDataAgentImpl
    private var mCinemaDatabase: CinemaRoomDatabase? = null

    fun initCinemaDatabase(context: Context) {
        mCinemaDatabase = CinemaRoomDatabase.getDBInstance(context)
    }

    override fun insertCities(
        onSuccess: (List<CitiesVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCities(onSuccess = {
            mCinemaDatabase?.getDao()?.insertCites(it)
        }, onFailure)
    }

    override fun getCities() = mCinemaDatabase?.getDao()?.getAllCities()

    override fun sendOTP(
        phone: String,
        onSuccess: (OTPResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.sendOTP(phone, onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun signInWithPhoneNumber(
        phone: String,
        otp: String,
        onSuccess: (OTPResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.signInWithPhoneNumber(
            phone, otp,
            onSuccess = {
                mCinemaDatabase?.getDao()?.insertSignInInformation(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getOtp() = mCinemaDatabase?.getDao()?.getSignInInformation()

    override fun getBanners(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {
        onSuccess(mCinemaDatabase?.getDao()?.getBanners() ?: listOf())
        mMovieDataAgent.getBanners(onSuccess = {
            mCinemaDatabase?.getDao()?.insertBanner(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mCinemaDatabase?.getDao()?.getMoviesByType(NOW_PLAYING_MOVIE) ?: listOf())
        mMovieDataAgent.getNowPlayingMovies(onSuccess = {

            it.forEach { movie ->
                movie.type = NOW_PLAYING_MOVIE
            }
            mCinemaDatabase?.getDao()?.insertMovies(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mCinemaDatabase?.getDao()?.getMoviesByType(COMING_SOON_MOVIE) ?: listOf())
        mMovieDataAgent.getComingSoonMovies(onSuccess = {

            it.forEach { movie ->
                movie.type = COMING_SOON_MOVIE
            }
            mCinemaDatabase?.getDao()?.insertMovies(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getMovieDetailsById(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDatabase?.getDao()?.getMovieById(movieId = movieId.toInt())?.let {
            onSuccess(it)
        }

        mMovieDataAgent.getMovieDetailsById(movieId, onSuccess = { movie ->
            val db = mCinemaDatabase?.getDao()?.getMovieById(movieId = movieId.toInt())
            movie.type = db?.type
            mCinemaDatabase?.getDao()?.insertSingleMovie(movie)
            onSuccess(movie)
        }, onFailure = onFailure)
    }

    override fun getCinemaTimeSlots(
        authorization: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCinemaTimeSlots(
            authorization = authorization,
            date = date,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun insertCinemaConfig(onSuccess: (List<ConfigVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getCinemaConfig(onSuccess = {
            mCinemaDatabase?.getDao()?.insertCinemaConfig(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getCinemaConfig(): List<ConfigVO>? = mCinemaDatabase?.getDao()?.getCinemaConfig()

}