package com.flexath.themoviebookingapp.data.vos.test

import com.google.gson.annotations.SerializedName

data class SnackCategoryVO(

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("deleted_at")
    val deletedAt: Any?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("is_active")
    val isActive: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("title_mm")
    val titleMm: String?,

    @SerializedName("updated_at")
    val updatedAt: String?
)