package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotColorVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SnackListTypeConverter {
    @TypeConverter
    fun toString(snackList: List<SnackVO>?) :String {
        return Gson().toJson(snackList)
    }

    @TypeConverter
    fun toSnackList(jsonString:String) : List<SnackVO>? {
        val snackListType = object : TypeToken<List<SnackVO>?>(){}.type
        return Gson().fromJson(jsonString,snackListType)
    }
}