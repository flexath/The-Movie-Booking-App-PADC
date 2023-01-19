package com.flexath.themoviebookingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.BannerHomeVIewHolder
import kotlinx.android.synthetic.main.view_holder_banner_home_list.view.*

class BannerHomeAdapter(private val bannerImageList:ArrayList<Int>) : RecyclerView.Adapter<BannerHomeVIewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHomeVIewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner_home_list,parent,false)
        return BannerHomeVIewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerHomeVIewHolder, position: Int) {
        val image = bannerImageList[position]
        holder.itemView.ivBannerImage.setImageResource(image)
    }

    override fun getItemCount(): Int {
        return bannerImageList.size
    }
}