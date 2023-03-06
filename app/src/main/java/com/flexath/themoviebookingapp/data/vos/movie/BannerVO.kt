package com.flexath.themoviebookingapp.data.vos.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("banners_table")
data class BannerVO(

    @SerializedName("created_at")
    @ColumnInfo("created_at")
    val createdAt: String?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("is_active")
    @ColumnInfo("is_active")
    val isActive: Int?,

    @SerializedName("title")
    @ColumnInfo("title")
    val title: String?,

    @SerializedName("updated_at")
    @ColumnInfo("updated_at")
    val updatedAt: String?,

    @SerializedName("url")
    @ColumnInfo("url")
    val url: String?
)