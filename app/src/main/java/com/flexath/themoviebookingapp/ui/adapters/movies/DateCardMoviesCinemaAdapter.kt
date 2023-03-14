package com.flexath.themoviebookingapp.ui.adapters.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.utils.TimeSlot
import com.flexath.themoviebookingapp.ui.viewholders.movies.DateCardMoviesCinemaViewHolder
import kotlinx.android.synthetic.main.view_holder_movies_cinema_date_cards_list.view.*

class DateCardMoviesCinemaAdapter(private val dateListTimeSlot: MutableList<String>,private val delegate: CinemaListViewHolderDelegate) : RecyclerView.Adapter<DateCardMoviesCinemaViewHolder>() {

    private var selectedPosition = 0
    private lateinit var mDateList:MutableList<TimeSlot>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCardMoviesCinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_cinema_date_cards_list,parent,false)
        return DateCardMoviesCinemaViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: DateCardMoviesCinemaViewHolder, position: Int) {

        holder.bindDate(mDateList[position],position)

        if (selectedPosition == position) {
            holder.itemView.dateCardTimeSlot.setBackgroundResource(R.drawable.date_card_accent_background)
            delegate.onClickTimeSlot(dateListTimeSlot[0])
        } else {
            holder.itemView.dateCardTimeSlot.setBackgroundResource(R.drawable.date_card_background)
        }

        holder.itemView.dateCardTimeSlot.setOnClickListener {
            // Reset the background color of the previously selected item
            val previousPosition = selectedPosition
            selectedPosition = holder.absoluteAdapterPosition
            notifyItemChanged(previousPosition)

            delegate.onClickTimeSlot(dateListTimeSlot[selectedPosition])
            holder.itemView.dateCardTimeSlot.setBackgroundResource(R.drawable.date_card_accent_background)
        }
    }

    override fun getItemCount(): Int {
        return mDateList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindTimeSlotData(dateList: MutableList<TimeSlot>) {
        mDateList = dateList
        notifyDataSetChanged()
    }
}