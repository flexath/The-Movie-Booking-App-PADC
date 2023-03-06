package com.flexath.themoviebookingapp.ui.viewholders.movies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import kotlinx.android.synthetic.main.view_holder_movies_home_banner_list.view.*

class BannerHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindBannerData(banner:BannerVO) {
        Glide.with(itemView.context)
            .load("${banner.url}")
            .into(itemView.ivBannerImage)
    }

}