package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.movies.CinemaTimesMoviesCinemaViewHolder

class CinemaTimesMoviesCinemaAdapter : RecyclerView.Adapter<CinemaTimesMoviesCinemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaTimesMoviesCinemaViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_cinema_times_list,parent,false)
        return CinemaTimesMoviesCinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemaTimesMoviesCinemaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}