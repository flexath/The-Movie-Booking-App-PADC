package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.SeatsMoviesSeatAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_seat.*
import kotlinx.android.synthetic.main.layout_gold_seat_movies_seat.*
import kotlinx.android.synthetic.main.layout_seat_side_type_movies_seat.view.*
import kotlinx.android.synthetic.main.view_holder_movies_seat_seats_list.view.*

class MoviesSeatFragment : Fragment() {

    private lateinit var mSeatsAdapter:SeatsMoviesSeatAdapter
    private var isSelectedSeat:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_seat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpListeners()
        setUpSeatsRecyclerView()
        setUpGoalSeatListeners()
    }

    private fun setUpListeners() {
        btnBuyButtonMoviesSeat.setOnClickListener {
            val action = MoviesSeatFragmentDirections.actionMoviesSeatToMoviesFood()
            it.findNavController().navigate(action)
        }
    }

    private fun setUpSeatsRecyclerView() {
        mSeatsAdapter = SeatsMoviesSeatAdapter()
        rvSeatsMoviesSeat.adapter = mSeatsAdapter
        rvSeatsMoviesSeat.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpGoalSeatListeners() {
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
        onClickCoupleSeat1()
        onClickCoupleSeat2()
        onClickCoupleSeat3()
        onClickCoupleSeat4()
    }

    private fun onClickSeat1() {
        ivGoldChairMoviesSeat1.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat1.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat1.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat2() {
        ivGoldChairMoviesSeat2.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat2.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat2.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat3() {
        ivGoldChairMoviesSeat3.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat3.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat3.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat4() {
        ivGoldChairMoviesSeat4.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat4.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat4.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat5() {
        ivGoldChairMoviesSeat5.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat5.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat5.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat6() {
        ivGoldChairMoviesSeat6.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat6.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat6.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat7() {
        ivGoldChairMoviesSeat7.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat7.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat7.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat8() {
        ivGoldChairMoviesSeat8.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat8.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat8.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat9() {
        ivGoldChairMoviesSeat9.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat9.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat9.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickSeat10() {
        ivGoldChairMoviesSeat10.setOnClickListener {
            if(isSelectedSeat){
                ivGoldChairMoviesSeat10.setImageResource(R.drawable.chair_available)
                isSelectedSeat = false
            }else{
                ivGoldChairMoviesSeat10.setImageResource(R.drawable.chair_taken)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickCoupleSeat1() {
        ivCoupleChairMoviesSeat1.setOnClickListener {
            if(isSelectedSeat){
                ivCoupleChairMoviesSeat1.setImageResource(R.drawable.chair_available_couple)
                isSelectedSeat = false
            }else{
                ivCoupleChairMoviesSeat1.setImageResource(R.drawable.chair_taken_couple)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickCoupleSeat2() {
        ivCoupleChairMoviesSeat2.setOnClickListener {
            if(isSelectedSeat){
                ivCoupleChairMoviesSeat2.setImageResource(R.drawable.chair_available_couple)
                isSelectedSeat = false
            }else{
                ivCoupleChairMoviesSeat2.setImageResource(R.drawable.chair_taken_couple)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickCoupleSeat3() {
        ivCoupleChairMoviesSeat3.setOnClickListener {
            if(isSelectedSeat){
                ivCoupleChairMoviesSeat3.setImageResource(R.drawable.chair_available_couple)
                isSelectedSeat = false
            }else{
                ivCoupleChairMoviesSeat3.setImageResource(R.drawable.chair_taken_couple)
                isSelectedSeat = true
            }
        }
    }

    private fun onClickCoupleSeat4() {
        ivCoupleChairMoviesSeat4.setOnClickListener {
            if(isSelectedSeat){
                ivCoupleChairMoviesSeat4.setImageResource(R.drawable.chair_available_couple)
                isSelectedSeat = false
            }else{
                ivCoupleChairMoviesSeat4.setImageResource(R.drawable.chair_taken_couple)
                isSelectedSeat = true
            }
        }
    }

}