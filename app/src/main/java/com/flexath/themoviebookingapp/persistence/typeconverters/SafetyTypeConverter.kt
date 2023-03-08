package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.FacilityVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotColorVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SafetyTypeConverter {
    @TypeConverter
    fun toString(safetyList: List<String>?) :String {
        return Gson().toJson(safetyList)
    }

    @TypeConverter
    fun toSafetyVO(jsonString:String) : List<String>? {
        val safetyType = object : TypeToken<List<String>?>(){}.type
        return Gson().fromJson(jsonString,safetyType)
    }
}