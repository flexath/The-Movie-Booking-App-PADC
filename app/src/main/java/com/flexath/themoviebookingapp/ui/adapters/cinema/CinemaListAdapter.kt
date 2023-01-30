package com.flexath.themoviebookingapp.ui.adapters.cinema

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.cinema.CinemaListViewHolder

class CinemaListAdapter : RecyclerView.Adapter<CinemaListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_cinemas_list,parent,false)
        return CinemaListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CinemaListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}