package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.FacilityVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotColorVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FacilityTypeConverter {
    @TypeConverter
    fun toString(facilityList: List<FacilityVO>?) :String {
        return Gson().toJson(facilityList)
    }

    @TypeConverter
    fun toFacilityVO(jsonString:String) : List<FacilityVO>? {
        val facilityType = object : TypeToken<List<FacilityVO>?>(){}.type
        return Gson().fromJson(jsonString,facilityType)
    }
}