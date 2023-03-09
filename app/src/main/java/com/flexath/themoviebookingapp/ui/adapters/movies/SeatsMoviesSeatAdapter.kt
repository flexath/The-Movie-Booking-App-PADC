package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.ui.viewholders.movies.seats.MoviesSeatViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_seat_list.view.*

class SeatsMoviesSeatAdapter : RecyclerView.Adapter<MoviesSeatViewHolder>() {

    private var mSeatDoubleList: MutableList<MutableList<SeatVO>> = mutableListOf()
    private val numberOfColumn = 18

    companion object {
        val seatVO = SeatVO(null,null,null,null,"path")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesSeatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movies_seat_list, parent, false)
        return MoviesSeatViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesSeatViewHolder, position: Int) {
        holder.bindSeatData(mSeatDoubleList,holder.absoluteAdapterPosition)
    }

    override fun getItemCount(): Int {
        return mSeatDoubleList.size * numberOfColumn
    }

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("NotifyDataSetChanged")
    fun bindNewData(seatDoubleList: List<List<SeatVO>>) {
        mSeatDoubleList = seatDoubleList as MutableList<MutableList<SeatVO>>
        mSeatDoubleList = addCinemaPath(mSeatDoubleList)
        notifyDataSetChanged()
    }

    private fun addCinemaPath(seatDoubleList: MutableList<MutableList<SeatVO>>) :MutableList<MutableList<SeatVO>>  {
        val mSeatDoubleList:MutableList<MutableList<SeatVO>> = mutableListOf()
        for (i in seatDoubleList.indices) {
            seatDoubleList[i].add(5, seatVO)
            seatDoubleList[i].add(6, seatVO)
            seatDoubleList[i].add(11, seatVO)
            seatDoubleList[i].add(12, seatVO)
            val mSeatSingleList:MutableList<SeatVO> = mutableListOf()
            for (ii in seatDoubleList[i].indices) {
                if(ii == 5 || ii == 6 || ii == 11 || ii == 12) {
                    mSeatSingleList.add(ii, seatVO)
                }else{
                    mSeatSingleList.add(ii,seatDoubleList[i][ii])
                }
            }
            mSeatDoubleList.add(mSeatSingleList)
        }
        return mSeatDoubleList
    }

}