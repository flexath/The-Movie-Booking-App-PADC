package com.flexath.themoviebookingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.MoviesHomeViewHolder

class MoviesHomeRecyclerAdapter : RecyclerView.Adapter<MoviesHomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_home_list,parent,false)
        return MoviesHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHomeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}