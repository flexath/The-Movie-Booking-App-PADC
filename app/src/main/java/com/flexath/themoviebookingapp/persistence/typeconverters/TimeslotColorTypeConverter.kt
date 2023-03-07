package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotColorVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimeslotColorTypeConverter {
    @TypeConverter
    fun toString(configTimeslot: List<TimeslotColorVO>?) :String {
        return Gson().toJson(configTimeslot)
    }

    @TypeConverter
    fun toTimeslotColorVO(jsonString:String) : List<TimeslotColorVO>? {
        val configTimeslotType = object : TypeToken<List<TimeslotColorVO>?>(){}.type
        return Gson().fromJson(jsonString,configTimeslotType)
    }
}