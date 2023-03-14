package com.flexath.themoviebookingapp.persistence.dao

import androidx.room.*
import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.CinemaInfoVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.network.responses.OTPResponse
import com.flexath.themoviebookingapp.data.vos.ticket.TicketInformation

@Dao
interface CinemaDao {

    // Location Screen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCites(cities:List<CitiesVO>)

    @Query("SELECT * FROM cities_table")
    fun getAllCities():List<CitiesVO>

    // Otp Screen - Sign In With Phone
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSignInInformation(otpList: OTPResponse?)

    @Query("SELECT * FROM otp_table WHERE code = :code")
    fun getSignInInformation(code:Int):OTPResponse

    // Movie Home Screen - Banner
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanner(banners:List<BannerVO>)

    @Query("SELECT * FROM banners_table")
    fun getBanners():List<BannerVO>

    // Movie Home Screen - Now Showing or Coming Soon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies:List<MovieVO>)

    @Query("SELECT * FROM movie_table")
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

    @Query("SELECT * FROM config_table WHERE key = :key")
    fun getCinemaConfig(key:String):ConfigVO

    // Cinema Info Screen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaInfo(config: List<CinemaInfoVO>)

    @Query("SELECT * FROM cinema_info_table WHERE id = :cinemaId")
    fun getCinemaInfo(cinemaId:Int):CinemaInfoVO?

    @Transaction
    @Query("DELETE FROM cities_table")
    fun deleteAllFromCitiesVO()

    @Transaction
    @Query("DELETE FROM otp_table")
    fun deleteAllFromOTP()

    @Transaction
    @Query("DELETE FROM banners_table")
    fun deleteAllFromBannerVO()

    @Transaction
    @Query("DELETE FROM movie_table")
    fun deleteAllFromMovieVO()

    @Transaction
    @Query("DELETE FROM config_table")
    fun deleteAllFromConfigVO()

    @Transaction
    @Query("DELETE FROM cinema_info_table")
    fun deleteAllFromCinemaInfoVO()

    @Transaction
    fun deleteAllEntities(){
        deleteAllFromCitiesVO()
        deleteAllFromOTP()
        deleteAllFromBannerVO()
        deleteAllFromMovieVO()
        deleteAllFromConfigVO()
        deleteAllFromCinemaInfoVO()
    }

    // Ticket Home
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicket(ticket: TicketInformation)

    @Query("SELECT * FROM ticket_table")
    fun getAllTickets():List<TicketInformation>

    @Query("DELETE FROM ticket_table WHERE id = :ticketId")
    fun deleteTicket(ticketId:Int)
}