package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.utils.TimeSlot
import kotlinx.android.synthetic.main.view_holder_movies_cinema_date_cards_list.view.*
import kotlinx.coroutines.NonDisposableHandle.parent
import java.time.LocalDate

class DateCardMoviesCinemaViewHolder(itemView: View,private val delegate: CinemaListViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mTimeSlot: TimeSlot? = null

    fun bindDate(date: TimeSlot,position:Int) {
        date.also { mTimeSlot = it }
        when (position) {
            0 -> {
                val today = "Today"
                itemView.tvDayOfWeekMoviesCinema.text = today
            }
            1 -> {
                val tomorrow = "Tomorrow"
                itemView.tvDayOfWeekMoviesCinema.text = tomorrow
            }
            else -> {
                itemView.tvDayOfWeekMoviesCinema.text = date.dayOfWeek
            }
        }
        itemView.tvMonthMoviesCinema.text = date.month
        itemView.tvDayOfMonthMoviesCinema.text = date.dayOfMonth
        mTimeSlot?.latestTime = date.latestTime
    }
}