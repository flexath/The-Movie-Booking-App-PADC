package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.DateCardsMoviesCinemaViewHolder

class DateCardsCinemaMoviesAdapter : RecyclerView.Adapter<DateCardsMoviesCinemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCardsMoviesCinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date_cards_movies_cinema_list,parent,false)
        return DateCardsMoviesCinemaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateCardsMoviesCinemaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}