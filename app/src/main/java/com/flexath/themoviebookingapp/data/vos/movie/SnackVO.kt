package com.flexath.themoviebookingapp.data.vos.movie

import com.google.gson.annotations.SerializedName

data class SnackVO(

    @SerializedName("category_id")
    val categoryId: Int?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("price")
    val price: Int?,

    var quantity: Int = 0
)