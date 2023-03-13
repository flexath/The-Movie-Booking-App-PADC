package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.ui.adapters.movies.CinemaTimesMoviesCinemaAdapter
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_movies_cinema_cinemas_list.view.*

class CinemasMoviesCinemaViewHolder(
    itemView: View,
    private val delegate: CinemaListViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {

    private var mCinemaTimesAdapter: CinemaTimesMoviesCinemaAdapter? = null
    private var mCinema: CinemaVO? = null

    init {
        itemView.setOnClickListener {
            if (mCinema?.isClicked == false) {
                itemView.rvCinemaTimesMoviesCinema.visibility = View.GONE
                itemView.llLongPressMoviesCinema.visibility = View.GONE
                mCinemaTimesAdapter = null
            } else {
                itemView.rvCinemaTimesMoviesCinema.visibility = View.VISIBLE
                itemView.llLongPressMoviesCinema.visibility = View.VISIBLE
                setUpCinemaTimesRecyclerView()
                requestTimeSlotData()
                delegate.getCinemaName(mCinema?.cinema)
                delegate.getCinemaId(mCinema?.cinemaId)
            }
        }

        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.tvSeeDetailsMoviesCinema.setOnClickListener {
            delegate.onClickCinemaSeeDetails(mCinema?.cinemaId ?: 0)
        }

    }

    private fun requestTimeSlotData() {
        mCinemaTimesAdapter?.setNewData(mCinema?.timeslots)
    }

    private fun setUpCinemaTimesRecyclerView() {
        mCinemaTimesAdapter = CinemaTimesMoviesCinemaAdapter(delegate)
        itemView.rvCinemaTimesMoviesCinema.adapter = mCinemaTimesAdapter
        itemView.rvCinemaTimesMoviesCinema.layoutManager =
            GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
    }

    fun bindData(cinema: CinemaVO) {
        mCinema = cinema
        itemView.tvCinemaNameMoviesCinema.text = cinema.cinema
    }
}