package com.flexath.themoviebookingapp.ui.viewholders

import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.CinemaTimesCinemaMoviesAdapter
import kotlinx.android.synthetic.main.fragment_movies_cinema.*
import kotlinx.android.synthetic.main.view_holder_movies_cinema_cinemas_list.view.*

class CinemasMoviesCinemaViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mCinemaTimesAdapter: CinemaTimesCinemaMoviesAdapter? = null
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
        mCinemaTimesAdapter = CinemaTimesCinemaMoviesAdapter()
        itemView.rvCinemaTimesMoviesCinema.adapter = mCinemaTimesAdapter
        itemView.rvCinemaTimesMoviesCinema.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)

    }
}