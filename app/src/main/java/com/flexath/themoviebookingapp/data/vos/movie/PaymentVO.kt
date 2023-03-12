package com.flexath.themoviebookingapp.data.vos.movie

import com.google.gson.annotations.SerializedName

data class PaymentVO(

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("deleted_at")
    val deletedAt: Any?,

    @SerializedName("icon")
    val icon: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("updated_at")
    val updatedAt: String?
)