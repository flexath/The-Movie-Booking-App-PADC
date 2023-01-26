package com.flexath.themoviebookingapp.ui.viewholders.seats

import android.view.View
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.layout_seat_side_type_movies_seat.view.*
import kotlinx.android.synthetic.main.view_holder_movies_seat_seats_list.view.*

class SeatExecutiveMoviesSeatViewHolder(itemView: View) : SeatBaseMoviesSeatViewHolder(itemView) {

    private var isSelectedSeat:Boolean = false
    init {
        setUpExecutiveSeatTitle()
        setUpExecutiveSeatRowName()
        setUpExecutiveSeatListeners()
    }

    private fun setUpExecutiveSeatRowName(){
        itemView.tvSeatFirstRowMoviesSeat.text = "C"
        itemView.tvSeatSecondRowMoviesSeat.text = "D"
        itemView.tvSeatTypeRight.tvSeatFirstRowMoviesSeat.text = "C"
        itemView.tvSeatTypeRight.tvSeatSecondRowMoviesSeat.text = "D"
    }

    private fun setUpExecutiveSeatListeners() {
        onClickSeat1()
        onClickSeat2()
        onClickSeat3()
        onClickSeat4()
        onClickSeat5()
        onClickSeat6()
        onClickSeat7()
        onClickSeat8()
        onClickSeat9()
        onClickSeat10()
        onClickSeat11()
        onClickSeat12()
        onClickSeat13()
        onClickSeat14()
        onClickSeat15()
        onClickSeat16()
    }

    private fun setUpExecutiveSeatTitle() {
        val executiveLabel = "Executive (6500ks)"
        itemView.tvSeatTypeTitleMoviesSeat.text = executiveLabel
    }

    private fun onClickSeat1(){
        itemView.ivChairMoviesSeat1.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat1.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat1.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat2() {
        itemView.ivChairMoviesSeat2.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat2.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat2.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat3() {
        itemView.ivChairMoviesSeat3.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat3.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat3.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat4() {
        itemView.ivChairMoviesSeat4.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat4.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat4.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat5() {
        itemView.ivChairMoviesSeat5.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat5.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat5.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat6() {
        itemView.ivChairMoviesSeat6.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat6.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat6.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat7() {
        itemView.ivChairMoviesSeat7.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat7.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat7.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat8() {
        itemView.ivChairMoviesSeat8.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat8.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat8.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat9() {
        itemView.ivChairMoviesSeat9.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat9.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat9.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat10() {
        itemView.ivChairMoviesSeat10.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat10.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat10.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat11() {
        itemView.ivChairMoviesSeat11.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat11.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat11.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat12() {
        itemView.ivChairMoviesSeat12.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat12.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat12.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat13() {
        itemView.ivChairMoviesSeat13.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat13.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat13.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat14() {
        itemView.ivChairMoviesSeat14.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat14.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat14.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat15() {
        itemView.ivChairMoviesSeat15.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat15.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat15.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat16() {
        itemView.ivChairMoviesSeat16.setOnClickListener {
            if(isSelectedSeat){
                itemView.ivChairMoviesSeat16.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                itemView.ivChairMoviesSeat16.setImageResource(R.drawable.chair_selected)
                isSelectedSeat = true
            }
        }
    }
}