package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewholders.movies.CinemasMoviesCinemaViewHolder

class CinemasMoviesCinemaAdapter(private val delegate:CinemaListViewHolderDelegate) : RecyclerView.Adapter<CinemasMoviesCinemaViewHolder>() {

    private var mCinemaList:List<CinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemasMoviesCinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_cinemas_list,parent,false)
        return CinemasMoviesCinemaViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: CinemasMoviesCinemaViewHolder, position: Int) {
        holder.bindData(mCinemaList[position])
    }

    override fun getItemCount(): Int {
        return mCinemaList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cinemaList:List<CinemaVO>) {
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }
}