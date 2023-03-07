package com.flexath.themoviebookingapp.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.network.responses.OTPResponse

@Dao
interface CinemaDao {

    // Location Screen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCites(cities:List<CitiesVO>)

    @Query("SELECT * from cities_table")
    fun getAllCities():List<CitiesVO>

    // Otp Screen - Sign In With Phone
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSignInInformation(otpList: OTPResponse?)

    @Query("SELECT * from otp_table")
    fun getSignInInformation():OTPResponse

    // Movie Home Screen - Banner
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanner(banners:List<BannerVO>)

    @Query("SELECT * from banners_table")
    fun getBanners():List<BannerVO>

    // Movie Home Screen - Now Showing or Coming Soon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies:List<MovieVO>)

    @Query("SELECT * from movie_table")
    fun getAllMovies():List<MovieVO>

    @Query("SELECT * FROM movie_table WHERE type = :type")
    fun getMoviesByType(type:String):List<MovieVO>

    // Movie Detail Screen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie: MovieVO?)

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    fun getMovieById(movieId:Int):MovieVO?

    // Movie Cinema Screen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaConfig(config: List<ConfigVO>)

    @Query("SELECT * FROM config_table")
    fun getCinemaConfig():List<ConfigVO>
}