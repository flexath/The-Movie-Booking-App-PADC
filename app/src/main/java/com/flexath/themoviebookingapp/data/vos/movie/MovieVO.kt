package com.flexath.themoviebookingapp.data.vos.movie

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.themoviebookingapp.persistence.typeconverters.GenreIdsTypeConverter
import com.flexath.themoviebookingapp.persistence.typeconverters.MovieCastsTypeConverter
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Entity("movie_table")
@TypeConverters(
    GenreIdsTypeConverter::class,
    MovieCastsTypeConverter::class
)
data class MovieVO(

    @SerializedName("genres")
    @ColumnInfo("genres")
    val genres: List<String>?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("original_title")
    @ColumnInfo("original_title")
    val originalTitle: String?,

    @SerializedName("poster_path")
    @ColumnInfo("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    @ColumnInfo("release_date")
    val releaseDate: String?,

    @SerializedName("casts")
    @ColumnInfo("casts")
    val casts: List<CastVO>?,

    @SerializedName("overview")
    @ColumnInfo("overview")
    val overview: String?,

    @SerializedName("rating")
    @ColumnInfo("rating")
    val rating: Double?,

    @SerializedName("runtime")
    @ColumnInfo("runtime")
    val runtime: Int?,

    @ColumnInfo("type")
    var type:String?
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun changeReleaseDateFormat(screenMovie:String): String {
        val date = getReleasingDateInstance()
        val day = date.dayOfMonth.toString()
        val month = date.month.toString()

        val dayWithThreeCharacters = getMonthFirstThreeCharacters(month)
        val dayWithSuffix = getDayWithSuffix(day.toInt())

        return if(screenMovie == "home") {
            "$dayWithSuffix\n$dayWithThreeCharacters"
        } else {
            "$dayWithThreeCharacters $dayWithSuffix, ${date.year}"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getReleasingDateInstance(): LocalDate {
        val releaseDateString = releaseDate?.replace('-', '/')
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH)
        return LocalDate.parse(releaseDateString, dateFormatter)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDayWithSuffix(day: Int): String {
        val suffixes = mapOf(1 to "st", 2 to "nd", 3 to "rd", 21 to "st", 22 to "nd", 23 to "rd", 31 to "st")
        val suffix = suffixes.getOrDefault(day % 100, "th")
        return "$day$suffix"
    }

    private fun getMonthFirstThreeCharacters(month: String): String {
        var monthStr = String()
        for (letter in 0..2) {
            monthStr += month[letter]
        }
        return monthStr
    }

    fun changeRunTimeMinToHour() :String {
        val hour = runtime?.div(60)
        val remainingMinutes = (runtime ?: 0) % 60
        return "$hour\bhr $remainingMinutes\bmin"
    }

    fun formatVoteAverage() :String {
        return "%.1f".format(rating)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMovieReleasingDayForNotification() :String {
        val calendar = Calendar.getInstance()

        // Current Date
        val currentDate = calendar.timeInMillis
        // Releasing Future Date
        val date = getReleasingDateInstance()
        calendar.set(date.year,date.month.value-1,date.dayOfMonth)
        val futureDate = calendar.timeInMillis
        val difference = futureDate - currentDate

        val day = difference / (1000 * 60 * 60 * 24)   // convert from millis-seconds to day
        if (day.toInt() == 0) {
            return "It is today"
        } else if (day.toInt() < 0) {
            return "It's already out"
        }
        return "Releasing in $day Days"
    }
}

const val NOW_PLAYING_MOVIE = "NOW_PLAYING"
const val COMING_SOON_MOVIE = "COMING_SOON"