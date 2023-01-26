package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.seats.SeatBaseMoviesSeatViewHolder
import com.flexath.themoviebookingapp.ui.viewholders.seats.SeatExecutiveMoviesSeatViewHolder
import com.flexath.themoviebookingapp.ui.viewholders.seats.SeatNormalMoviesSeatViewHolder
import com.flexath.themoviebookingapp.ui.viewholders.seats.SeatPremiumMoviesSeatViewHolder

class SeatsMoviesSeatAdapter : RecyclerView.Adapter<SeatBaseMoviesSeatViewHolder>() {

    private val VIEW_TYPE_NORMAL_SEAT = 0
    private val VIEW_TYPE_EXECUTIVE_SEAT = 1
    private val VIEW_TYPE_PREMIUM_SEAT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatBaseMoviesSeatViewHolder {

        return when(viewType) {
            VIEW_TYPE_NORMAL_SEAT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_seat_seats_list,parent,false)
                SeatNormalMoviesSeatViewHolder(view)
            }
            VIEW_TYPE_EXECUTIVE_SEAT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_seat_seats_list,parent,false)
                SeatExecutiveMoviesSeatViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_seat_seats_list,parent,false)
                SeatPremiumMoviesSeatViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: SeatBaseMoviesSeatViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> VIEW_TYPE_NORMAL_SEAT
            1 -> VIEW_TYPE_EXECUTIVE_SEAT
            else -> VIEW_TYPE_PREMIUM_SEAT
        }
    }
}