package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.movies.CinemasMoviesCinemaViewHolder

class CinemasMoviesCinemaAdapter : RecyclerView.Adapter<CinemasMoviesCinemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemasMoviesCinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_cinemas_list,parent,false)
        return CinemasMoviesCinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemasMoviesCinemaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}