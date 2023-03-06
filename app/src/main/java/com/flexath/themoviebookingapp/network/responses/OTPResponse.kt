package com.flexath.themoviebookingapp.network.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.themoviebookingapp.data.vos.signin.OtpVO
import com.flexath.themoviebookingapp.persistence.typeconverters.OtpTypeConverter
import com.google.gson.annotations.SerializedName

@Entity("otp_table")
@TypeConverters(
    OtpTypeConverter::class
)
data class OTPResponse(

    @SerializedName("code")
    @PrimaryKey
    val code: Int?,

    @SerializedName("message")
    @ColumnInfo("message")
    val message: String?,

    @SerializedName("token")
    @ColumnInfo("token")
    val token: String?,

    @SerializedName("data")
    @ColumnInfo("data")
    val data: OtpVO?,
)