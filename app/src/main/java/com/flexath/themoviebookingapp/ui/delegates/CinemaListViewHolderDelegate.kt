package com.flexath.themoviebookingapp.ui.delegates

interface CinemaListViewHolderDelegate {
    fun onClickCinemaSeeDetails()
    fun onClickCinemaTimes()
    fun onClickTimeSlot(date:String)
}