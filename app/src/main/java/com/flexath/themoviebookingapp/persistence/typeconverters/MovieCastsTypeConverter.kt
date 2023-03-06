package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieCastsTypeConverter {
    @TypeConverter
    fun toString(casts: List<CastVO>?) :String {
        return Gson().toJson(casts)
    }

    @TypeConverter
    fun toCastVO(jsonString:String) : List<CastVO>? {
        val movieCastsType = object : TypeToken<List<CastVO>?>(){}.type
        return Gson().fromJson(jsonString,movieCastsType)
    }
}