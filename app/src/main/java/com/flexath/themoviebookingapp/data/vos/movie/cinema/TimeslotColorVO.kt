package com.flexath.themoviebookingapp.data.vos.movie.cinema

import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap
import kotlinx.serialization.Serializable

data class TimeslotColorVO(
    val id: Int?,
    val title: String?,
    val color: String?
)
