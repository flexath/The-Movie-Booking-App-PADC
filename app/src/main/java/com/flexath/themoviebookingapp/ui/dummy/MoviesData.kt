package com.flexath.themoviebookingapp.ui.dummy

import com.flexath.themoviebookingapp.R

class MoviesData{
    val advertisementImages = arrayListOf(R.drawable.kfc_banner,R.drawable.pizza_banner,R.drawable.coffee_banner,R.drawable.food)
}

data class MoviesInfo(
    val image:Int = R.drawable.blackwidow_poster,
    val title:String = "Black Widow",
    val rating:Float = 7.1f,
    val film:String = "2D, 3D, 3D IMAX, DBOX 3D"
)