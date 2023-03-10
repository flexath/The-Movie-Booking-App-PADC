package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.test.SnackVO
import com.flexath.themoviebookingapp.ui.viewholders.movies.MoviesFoodViewHolder

class MoviesSnackAdapter : RecyclerView.Adapter<MoviesFoodViewHolder>() {

    private var mSnackList:List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesFoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_food_foods_list,parent,false)
        return MoviesFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesFoodViewHolder, position: Int) {
        holder.bindSnackData(mSnackList[position])
    }

    override fun getItemCount(): Int {
        return mSnackList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindSnackListData(snackList: List<SnackVO>) {
        mSnackList = snackList
        notifyDataSetChanged()
    }
}