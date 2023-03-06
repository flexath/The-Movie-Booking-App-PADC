package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.ui.viewholders.movies.CastMoviesDetailsViewHolder

class CastMoviesDetailsAdapter : RecyclerView.Adapter<CastMoviesDetailsViewHolder>() {

    private var mCasts:List<CastVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMoviesDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_details_cast_list,parent,false)
        return CastMoviesDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastMoviesDetailsViewHolder, position: Int) {
        holder.bindData(mCasts[position])
    }

    override fun getItemCount(): Int {
        return 10
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindCastData(casts: List<CastVO>) {
        mCasts = casts
        notifyDataSetChanged()
    }
}