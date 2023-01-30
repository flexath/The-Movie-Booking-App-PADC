package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.DateCardMoviesCinemaAdapter
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewpods.CinemaListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_cinema.*


class MoviesCinemaFragment : Fragment(),CinemaListViewHolderDelegate {

    private lateinit var mDateCardsAdapter:DateCardMoviesCinemaAdapter
//    private lateinit var mCinemasAdapter: CinemasMoviesCinemaAdapter

    private lateinit var cinemaListViewPod: CinemaListViewPod

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_cinema, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpDateCardsRecyclerView()            // For Date Cards
        //setUpCinemasRecyclerView()
        setUpViewPod()
    }

    private fun setUpDateCardsRecyclerView() {
        mDateCardsAdapter = DateCardMoviesCinemaAdapter()
        rvDatesCardsMoviesCinema.adapter = mDateCardsAdapter
        rvDatesCardsMoviesCinema.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        mDateCardsAdapter.notifyDataSetChanged()
    }

    private fun setUpViewPod() {
        cinemaListViewPod = vpMoviesCinema as CinemaListViewPod
        cinemaListViewPod.setUpMovieListViewPod(this)
    }

    override fun onClickCinemaSeeDetails() {
        val action = MoviesCinemaFragmentDirections.actionChooseCinemaToCinemaInfo()
        findNavController().navigate(action)
    }

    override fun onClickCinemaTimes() {
        val action = MoviesCinemaFragmentDirections.actionChooseCinemaToMoviesSeat()
        findNavController().navigate(action)
    }


//    private fun setUpCinemasRecyclerView() {
//        mCinemasAdapter = CinemasMoviesCinemaAdapter()
//        rvCinemasMoviesCinema.adapter = mCinemasAdapter
//        val linearLayoutManager = LinearLayoutManager(requireContext())
//        rvCinemasMoviesCinema.layoutManager = linearLayoutManager
//
//        val divider = DividerItemDecoration((activity as AppCompatActivity).applicationContext, linearLayoutManager.orientation)
//        rvCinemasMoviesCinema.addItemDecoration(divider)
//        mCinemasAdapter.notifyDataSetChanged()
//    }
}