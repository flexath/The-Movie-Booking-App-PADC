package com.flexath.themoviebookingapp.data.vos.movie.cinema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.themoviebookingapp.persistence.typeconverters.TimeslotColorTypeConverter
import com.google.gson.annotations.SerializedName

@Entity("config_table")
@TypeConverters(
    TimeslotColorTypeConverter::class
)
data class ConfigVO(

    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("key")
    @ColumnInfo("key")
    val key: String?,

    @SerializedName("value")
    @ColumnInfo("value")
    val value: List<TimeslotColorVO>?
)