package com.flexath.themoviebookingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.CastMoviesDetailsViewHolder

class DetailsMoviesCastAdapter : RecyclerView.Adapter<CastMoviesDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMoviesDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast_movies_details_list,parent,false)
        return CastMoviesDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastMoviesDetailsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}