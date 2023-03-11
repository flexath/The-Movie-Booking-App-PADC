package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotColorVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ValueAnyTypeConverter {
    @TypeConverter
    fun toString(configAnyTimeslot: Any?) :String {
        return Gson().toJson(configAnyTimeslot)
    }

    @TypeConverter
    fun toTimeslotAnyColorVO(jsonString:String) : Any? {
        val configTimeslotAnyType = object : TypeToken<Any?>(){}.type
        return Gson().fromJson(jsonString,configTimeslotAnyType)
    }
}