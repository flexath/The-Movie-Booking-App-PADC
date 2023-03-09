package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotVO
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesCinemaFragmentDirections
import kotlinx.android.synthetic.main.view_holder_movies_cinema_cinema_times_list.view.*

class CinemaTimesMoviesCinemaViewHolder(
    itemView: View,
    private val delegate: CinemaListViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var isSelectedShowTime = false
    private var mCinemaModel: CinemaModel = CinemaModelImpl
    private var mTimeslot:TimeslotVO? = null

    init {
        setUpOnClickListener()
        setUpOnLongClickListener()
    }

    private fun setUpOnClickListener() {
        itemView.setOnClickListener {
            if (isSelectedShowTime) {
                itemView.itemCinemaTimes.setBackgroundResource(R.drawable.movies_show_times_background)
                isSelectedShowTime = false
            } else {
                itemView.itemCinemaTimes.setBackgroundResource(R.drawable.item_cinema_times_orange_background)
                Toast.makeText(itemView.context, "Selected", Toast.LENGTH_SHORT).show()
                isSelectedShowTime = true
            }
        }
    }

    private fun setUpOnLongClickListener() {
        itemView.setOnLongClickListener {
            mTimeslot?.cinemaDayTimeslotId?.let { id ->
                delegate.onClickCinemaTimes(id)
            }
            true
        }
    }

    fun bindData(timeslot: TimeslotVO) {
        mTimeslot = timeslot
        itemView.tvStartTimeMovieCinemaTimeslot.text = timeslot.start_time
    }
}