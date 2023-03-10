package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.ui.delegates.SeatViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewholders.movies.seats.MoviesSeatViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_seat_list.view.*

class SeatsMoviesSeatAdapter(private val delegate:SeatViewHolderDelegate) : RecyclerView.Adapter<MoviesSeatViewHolder>() {

    private var mSeatDoubleList: MutableList<MutableList<SeatVO>> = mutableListOf()
    private val numberOfColumn = 18

    companion object {
        private val seatVO = SeatVO(null,null,null,null,"path",false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesSeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movies_seat_list, parent, false)
        return MoviesSeatViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: MoviesSeatViewHolder, position: Int) {
        holder.bindSeatData(mSeatDoubleList,holder.absoluteAdapterPosition)
    }

    override fun getItemCount(): Int {
        return mSeatDoubleList.size * numberOfColumn
    }

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("NotifyDataSetChanged")
    fun bindNewData(seatDoubleList: MutableList<MutableList<SeatVO>>) {
        mSeatDoubleList = seatDoubleList
        Log.i("SeatDouble",mSeatDoubleList.toString())
        notifyDataSetChanged()
    }
}