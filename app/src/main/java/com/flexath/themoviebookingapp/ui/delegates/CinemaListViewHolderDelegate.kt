package com.flexath.themoviebookingapp.ui.delegates

interface CinemaListViewHolderDelegate {
    fun onClickCinemaSeeDetails(cinemaId:Int)
    fun onClickCinemaTimes(dayTimeslotId:Int,cinemaTime:String?)
    fun onClickTimeSlot(date:String)

    fun getCinemaName(cinemaName:String?)
    fun getCinemaId(cinemaId: Int?)
}