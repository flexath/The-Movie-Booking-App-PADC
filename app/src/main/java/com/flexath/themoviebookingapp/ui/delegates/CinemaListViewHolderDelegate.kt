package com.flexath.themoviebookingapp.ui.delegates

interface CinemaListViewHolderDelegate {
    fun onClickCinemaSeeDetails(cinemaId:Int)
    fun onClickCinemaTimes()
    fun onClickTimeSlot(date:String)
}