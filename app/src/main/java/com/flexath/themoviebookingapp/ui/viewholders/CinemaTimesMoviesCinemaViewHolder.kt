package com.flexath.themoviebookingapp.ui.viewholders

import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.fragments.movies.MoviesCinemaFragmentDirections
import kotlinx.android.synthetic.main.view_holder_movies_cinema_cinema_times_list.view.*

class CinemaTimesMoviesCinemaViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var isSelectedShowTime = false

    init {
        setUpOnClickListener()
        setUpOnLongClickListener()
    }

    private fun setUpOnClickListener(){
        itemView.setOnClickListener {
            if(isSelectedShowTime){
                itemView.itemCinemaTimes.setBackgroundResource(R.drawable.movies_show_times_background)
                isSelectedShowTime = false
            }else{
                itemView.itemCinemaTimes.setBackgroundResource(R.drawable.item_cinema_times_orange_background)
                Toast.makeText(itemView.context,"Selected",Toast.LENGTH_SHORT).show()
                isSelectedShowTime = true
            }
        }
    }

    private fun setUpOnLongClickListener(){
        itemView.setOnLongClickListener {
            val action = MoviesCinemaFragmentDirections.actionChooseCinemaToMoviesSeat()
            it.findNavController().navigate(action)
            true
        }
    }

}