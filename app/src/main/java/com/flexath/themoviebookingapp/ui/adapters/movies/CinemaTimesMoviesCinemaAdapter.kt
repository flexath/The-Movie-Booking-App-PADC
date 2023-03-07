package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.cinema.TimeslotVO
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewholders.movies.CinemaTimesMoviesCinemaViewHolder

class CinemaTimesMoviesCinemaAdapter(private val delegate: CinemaListViewHolderDelegate) : RecyclerView.Adapter<CinemaTimesMoviesCinemaViewHolder>() {

    private var mTimeSlots:List<TimeslotVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaTimesMoviesCinemaViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_cinema_times_list,parent,false)
        return CinemaTimesMoviesCinemaViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: CinemaTimesMoviesCinemaViewHolder, position: Int) {
        holder.bindData(mTimeSlots[position])
    }

    override fun getItemCount(): Int {
        return mTimeSlots.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeslots: List<TimeslotVO>?) {
        if (timeslots != null) {
            mTimeSlots = timeslots
        }
        notifyDataSetChanged()
    }
}