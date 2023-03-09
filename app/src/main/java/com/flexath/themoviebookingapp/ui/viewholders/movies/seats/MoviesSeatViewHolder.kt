package com.flexath.themoviebookingapp.ui.viewholders.movies.seats

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.SeatVO
import com.flexath.themoviebookingapp.ui.adapters.movies.SeatsMoviesSeatAdapter
import kotlinx.android.synthetic.main.view_holder_movies_seat_list.view.*

class MoviesSeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val numberOfColumn = 18

    fun bindSeatData(seatDoubleList: MutableList<MutableList<SeatVO>>,position: Int) {

        val row = position / numberOfColumn
        val column = position % numberOfColumn
        val seat = seatDoubleList[row][column]

        when(seat.type) {
            "text" -> {
                itemView.tvSeatTypeCinemaSeat.visibility = View.VISIBLE
                itemView.ivSeatCinemaSeat.visibility = View.INVISIBLE
                itemView.tvSeatTypeCinemaSeat.text = seat.symbol
            }
            "space","path" -> {
                itemView.tvSeatTypeCinemaSeat.visibility = View.INVISIBLE
                itemView.ivSeatCinemaSeat.visibility = View.INVISIBLE
            }
            "taken" -> {
                itemView.tvSeatTypeCinemaSeat.visibility = View.INVISIBLE
                itemView.ivSeatCinemaSeat.visibility = View.VISIBLE
                itemView.ivSeatCinemaSeat.setImageResource(R.drawable.chair_taken)
            }
            "available" -> {
                itemView.tvSeatTypeCinemaSeat.visibility = View.INVISIBLE
                itemView.ivSeatCinemaSeat.visibility = View.VISIBLE
                itemView.ivSeatCinemaSeat.setImageResource(R.drawable.chair_available)
            }
        }
    }
}