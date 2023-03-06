package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.network.utils.IMG_BASE_URL
import com.flexath.themoviebookingapp.ui.delegates.MoviesListViewHolderDelegate
import kotlinx.android.synthetic.main.fragment_movies_details.*
import kotlinx.android.synthetic.main.view_holder_movies_home_banner_list.view.*
import kotlinx.android.synthetic.main.view_holder_movies_home_movies_list.view.*

class MoviesHomeViewHolder(itemView: View,private val delegate:MoviesListViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie:MovieVO? = null

    init {
        this.itemView.setOnClickListener {
            delegate.onTapMovie(mMovie?.id)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun bindData(movie:MovieVO) {
        mMovie = movie

        Glide.with(itemView.context)
            .load("$IMG_BASE_URL${movie.posterPath}")
            .into(itemView.ivPosterMoviesHome)

        itemView.tvTitleMoviesHome.text = movie.originalTitle
        itemView.tvDateMovieHome.text = movie.changeReleaseDateFormat("home")
    }

}