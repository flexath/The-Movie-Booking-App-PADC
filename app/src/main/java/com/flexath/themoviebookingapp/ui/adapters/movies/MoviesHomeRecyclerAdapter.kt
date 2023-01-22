package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment.Companion.CINEMA_TIME_EXTRA_KEY
import com.flexath.themoviebookingapp.ui.viewholders.MoviesHomeViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_home_list.view.*

class MoviesHomeRecyclerAdapter(private val tabLayoutMoviesHomeFragment: TabLayoutMoviesHomeFragment) :
    RecyclerView.Adapter<MoviesHomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movies_home_list, parent, false)
        return MoviesHomeViewHolder(view,tabLayoutMoviesHomeFragment)
    }

    override fun onBindViewHolder(holder: MoviesHomeViewHolder, position: Int) {
        if (tabLayoutMoviesHomeFragment.getMovieDateBundle()?.getBoolean(CINEMA_TIME_EXTRA_KEY) == true) {
            holder.itemView.rlDateMovieHome.visibility = View.VISIBLE
        } else {
            holder.itemView.rlDateMovieHome.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return 7
    }
}