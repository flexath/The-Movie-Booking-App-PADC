package com.flexath.themoviebookingapp.ui.delegates

interface CinemaListViewHolderDelegate {
    fun onClickCinemaSeeDetails(cinemaId:Int)
    fun onClickCinemaTimes(dayTimeslotId:Int)
    fun onClickTimeSlot(date:String)
}