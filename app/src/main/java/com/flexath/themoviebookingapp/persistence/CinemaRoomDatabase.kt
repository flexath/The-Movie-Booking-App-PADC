package com.flexath.themoviebookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flexath.themoviebookingapp.data.vos.location.CitiesVO
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.data.vos.movie.CinemaInfoVO
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.ConfigVO
import com.flexath.themoviebookingapp.network.responses.OTPResponse
import com.flexath.themoviebookingapp.persistence.dao.CinemaDao

@Database(entities = [CitiesVO::class,OTPResponse::class,
    BannerVO::class,MovieVO::class,ConfigVO::class,CinemaInfoVO::class], version = 4 , exportSchema = false)
abstract class CinemaRoomDatabase : RoomDatabase() {

    abstract fun getDao(): CinemaDao

    companion object {
        private var roomDB:CinemaRoomDatabase? =  null
        private const val dbName = "CinemaDB"

        fun getDBInstance(context: Context) : CinemaRoomDatabase? {
            when(roomDB) {
                null -> {
                    roomDB = Room.databaseBuilder(context,CinemaRoomDatabase::class.java, dbName)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return roomDB
        }
    }
}