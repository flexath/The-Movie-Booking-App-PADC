package com.flexath.themoviebookingapp.data.model

import android.content.Context
import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.*
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.data.vos.test.SnackCategoryVO
import com.flexath.themoviebookingapp.data.vos.test.SnackVO
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
            onSuccess(it)
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

    override fun getOtp(code:Int) = mCinemaDatabase?.getDao()?.getSignInInformation(code)

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

    override fun insertCinemaConfig(
        onSuccess: (List<ConfigVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCinemaConfig(onSuccess = {
            mCinemaDatabase?.getDao()?.insertCinemaConfig(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getCinemaConfig(): List<ConfigVO>? = mCinemaDatabase?.getDao()?.getCinemaConfig()

    override fun insertCinemaInfo(
        onSuccess: (List<CinemaInfoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCinemaInfo(onSuccess = {
            mCinemaDatabase?.getDao()?.insertCinemaInfo(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getCinemaInfo(cinemaId:Int) = mCinemaDatabase?.getDao()?.getCinemaInfo(cinemaId)

    override fun getSeatPlan(
        authorization: String,
        dayTimeSlotId: Int,
        bookingDate: String,
        onSuccess: (MutableList<MutableList<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getSeatPlan(authorization,dayTimeSlotId,bookingDate,onSuccess,onFailure)
    }

    override fun getSnackCategory(
        authorization: String,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getSnackCategory(authorization,onSuccess,onFailure)
    }

    override fun getSnackByCategory(
        authorization: String,
        categoryId: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getSnackByCategory(authorization,categoryId,onSuccess,onFailure)
    }

}