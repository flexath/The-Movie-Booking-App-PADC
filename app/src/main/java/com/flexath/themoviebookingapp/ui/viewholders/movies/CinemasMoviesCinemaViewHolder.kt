package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.ui.adapters.movies.CinemaTimesMoviesCinemaAdapter
import kotlinx.android.synthetic.main.view_holder_movies_cinema_cinemas_list.view.*

class CinemasMoviesCinemaViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mCinemaTimesAdapter: CinemaTimesMoviesCinemaAdapter? = null
    private var isVisibleRecyclerView:Boolean = false

    init {
        itemView.setOnClickListener {
            if(isVisibleRecyclerView) {
                itemView.rvCinemaTimesMoviesCinema.visibility = View.GONE
                itemView.llLongPressMoviesCinema.visibility = View.GONE
                mCinemaTimesAdapter = null
                isVisibleRecyclerView = false
            }else{
                itemView.rvCinemaTimesMoviesCinema.visibility = View.VISIBLE
                itemView.llLongPressMoviesCinema.visibility = View.VISIBLE
                setUpCinemaTimesRecyclerView()
                isVisibleRecyclerView = true
            }
        }
    }

    private fun setUpCinemaTimesRecyclerView() {
        mCinemaTimesAdapter = CinemaTimesMoviesCinemaAdapter()
        itemView.rvCinemaTimesMoviesCinema.adapter = mCinemaTimesAdapter
        itemView.rvCinemaTimesMoviesCinema.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)

    }
}