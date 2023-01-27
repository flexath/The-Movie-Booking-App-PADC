package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesHomeFragmentDirections
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment
import com.flexath.themoviebookingapp.ui.fragments.movies.TabLayoutMoviesHomeFragment.Companion.CINEMA_TIME_EXTRA_KEY

class MoviesHomeViewHolder(
    itemView: View,
    tabLayoutMoviesHomeInstance: TabLayoutMoviesHomeFragment
) : RecyclerView.ViewHolder(itemView) {
    init {
        this.itemView.setOnClickListener {
            val action = MoviesHomeFragmentDirections.actionMoviesHomeToMoviesDetailsHome()
            action.argNowShowingOrComingSoon = tabLayoutMoviesHomeInstance.getMovieDateBundle()?.getBoolean(CINEMA_TIME_EXTRA_KEY) == true
            it.findNavController().navigate(action)
        }
    }
}