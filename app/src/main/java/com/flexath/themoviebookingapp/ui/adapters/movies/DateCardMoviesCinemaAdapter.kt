package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.movies.DateCardMoviesCinemaViewHolder

class DateCardMoviesCinemaAdapter : RecyclerView.Adapter<DateCardMoviesCinemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCardMoviesCinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_date_cards_list,parent,false)
        return DateCardMoviesCinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateCardMoviesCinemaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}