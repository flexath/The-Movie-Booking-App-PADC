package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.adapters.movies.SeatsMoviesSeatAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_seat.*

class MoviesSeatFragment : Fragment() {

    private lateinit var mSeatsAdapter: SeatsMoviesSeatAdapter
    private val mCinemaModel: CinemaModel = CinemaModelImpl
    private val args: MoviesSeatFragmentArgs by navArgs()
    private var dayTimeSlotId: Int = 0
    private var bookingDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_seat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        dayTimeSlotId = args.argDayTimeslotId
        bookingDate = args.argBookingDate

        setUpListeners()

        setUpSeatsRecyclerView()
        requestData()
    }

    private fun requestData() {
        bookingDate?.let {
            mCinemaModel.getSeatPlan(
                "Bearer ${mCinemaModel.getOtp(201)?.token}", dayTimeSlotId, it,
                onSuccess = { seatDoubleList ->
                    Toast.makeText(
                        requireActivity(),
                        "Seating Call succeeded - ${seatDoubleList.size}",
                        Toast.LENGTH_SHORT
                    ).show()
                    mSeatsAdapter.bindNewData(seatDoubleList)
                },
                onFailure = {
                    Toast.makeText(requireActivity(), "Seating Call fails", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpSeatsRecyclerView() {
        mSeatsAdapter = SeatsMoviesSeatAdapter()
        rvSeatsMoviesSeat.adapter = mSeatsAdapter
        rvSeatsMoviesSeat.layoutManager =
            GridLayoutManager(requireContext(), 18, GridLayoutManager.VERTICAL, false)
        rvSeatsMoviesSeat.setHasFixedSize(true)
        mSeatsAdapter.notifyDataSetChanged()
    }

    private fun setUpListeners() {
        btnBuyButtonMoviesSeat.setOnClickListener {
            val action = MoviesSeatFragmentDirections.actionMoviesSeatToMoviesFood()
            it.findNavController().navigate(action)
        }
    }

}