package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.ui.delegates.MoviesListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewholders.movies.MoviesHomeViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_home_movies_list.view.*

class MoviesHomeRecyclerAdapter(private val tabPosition: Int, private val delegate: MoviesListViewHolderDelegate) :RecyclerView.Adapter<MoviesHomeViewHolder>() {

    private var mMovieList:List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movies_home_movies_list, parent, false)
        return MoviesHomeViewHolder(view, delegate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MoviesHomeViewHolder, position: Int) {
        if (tabPosition == 0) {
            holder.itemView.rlDateMovieHome.visibility = View.INVISIBLE
        } else {
            holder.itemView.rlDateMovieHome.visibility = View.VISIBLE
        }
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}