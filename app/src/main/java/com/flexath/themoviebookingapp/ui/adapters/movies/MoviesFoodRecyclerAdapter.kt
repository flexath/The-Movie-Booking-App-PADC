package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.MoviesFoodViewHolder

class MoviesFoodRecyclerAdapter : RecyclerView.Adapter<MoviesFoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_food_foods_list,parent,false)
        return MoviesFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesFoodViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}