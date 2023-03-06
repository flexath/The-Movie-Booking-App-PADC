package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OtpTypeConverter {
    @TypeConverter
    fun toString(otp: OtpVO?) :String {
        return Gson().toJson(otp)
    }

    @TypeConverter
    fun toOtpVO(jsonString:String) : OtpVO? {
        val otpType = object : TypeToken<OtpVO?>(){}.type
        return Gson().fromJson(jsonString,otpType)
    }
}