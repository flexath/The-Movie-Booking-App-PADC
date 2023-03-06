package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesPaymentFragmentDirections

class MoviesPaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            val action = MoviesPaymentFragmentDirections.actionProfilePaymentToMoviesTicketConfirmation()
            it.findNavController().navigate(action)
        }
    }
}