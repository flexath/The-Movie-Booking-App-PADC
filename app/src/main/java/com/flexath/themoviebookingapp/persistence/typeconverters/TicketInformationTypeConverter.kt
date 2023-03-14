package com.flexath.themoviebookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotColorVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutVO
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TicketInformationTypeConverter {
    @TypeConverter
    fun toString(ticket: TicketCheckoutVO?) :String {
        return Gson().toJson(ticket)
    }

    @TypeConverter
    fun toTicketInformation(jsonString:String) : TicketCheckoutVO? {
        val ticketType = object : TypeToken<TicketCheckoutVO?>(){}.type
        return Gson().fromJson(jsonString,ticketType)
    }
}