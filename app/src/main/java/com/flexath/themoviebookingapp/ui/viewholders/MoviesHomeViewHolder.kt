package com.flexath.themoviebookingapp.ui.viewholders

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesHomeFragmentDirections

class MoviesHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        this.itemView.setOnClickListener {
            val action = MoviesHomeFragmentDirections.actionMoviesHomeToMoviesDetailsHome()
            it.findNavController().navigate(action)
        }
    }
}