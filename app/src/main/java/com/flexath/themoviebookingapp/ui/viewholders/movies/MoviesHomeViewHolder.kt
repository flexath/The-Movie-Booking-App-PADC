package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.ui.delegates.MoviesListViewHolderDelegate

class MoviesHomeViewHolder(
    itemView: View,
    delegate:MoviesListViewHolderDelegate
) : RecyclerView.ViewHolder(itemView) {
    init {
        this.itemView.setOnClickListener {
            delegate.onTapMovie()
        }
    }
}