package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.movie.CastVO
import com.flexath.themoviebookingapp.network.utils.IMG_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movies_details_cast_list.view.*

class CastMoviesDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(castVO: CastVO) {
        Glide.with(itemView.context)
            .load("$IMG_BASE_URL${castVO.profilePath}")
            .into(itemView.civCastPictureMoviesDetails)

        itemView.tvCastNameMoviesDetails.text = castVO.name
    }

}