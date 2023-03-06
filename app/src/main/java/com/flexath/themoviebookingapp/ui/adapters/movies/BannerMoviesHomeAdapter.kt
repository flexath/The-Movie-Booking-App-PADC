package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.BannerVO
import com.flexath.themoviebookingapp.ui.viewholders.movies.BannerHomeViewHolder

class BannerMoviesHomeAdapter(private val bannerImageList:List<BannerVO>) : RecyclerView.Adapter<BannerHomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_home_banner_list,parent,false)
        return BannerHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerHomeViewHolder, position: Int) {
        val banner = bannerImageList[position]
        holder.bindBannerData(banner)
    }

    override fun getItemCount(): Int {
        return bannerImageList.size
    }
}