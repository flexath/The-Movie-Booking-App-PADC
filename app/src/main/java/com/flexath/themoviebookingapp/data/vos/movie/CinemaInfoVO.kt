package com.flexath.themoviebookingapp.data.vos.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.themoviebookingapp.persistence.typeconverters.FacilityTypeConverter
import com.flexath.themoviebookingapp.persistence.typeconverters.SafetyTypeConverter
import com.google.gson.annotations.SerializedName

@Entity("cinema_info_table")
@TypeConverters(
    FacilityTypeConverter::class,
    SafetyTypeConverter::class
)
data class CinemaInfoVO(

    @SerializedName("address")
    @ColumnInfo("address")
    val address: String?,

    @SerializedName("email")
    @ColumnInfo("email")
    val email: String?,

    @SerializedName("facilities")
    @ColumnInfo("facilities")
    val facilities: List<FacilityVO>?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo("name")
    val name: String?,

    @SerializedName("phone")
    @ColumnInfo("phone")
    val phone: String?,

    @SerializedName("promo_vdo_url")
    @ColumnInfo("promo_vdo_url")
    val promoVdoUrl: String?,

    @SerializedName("safety")
    @ColumnInfo("safety")
    val safety: List<String>?
)