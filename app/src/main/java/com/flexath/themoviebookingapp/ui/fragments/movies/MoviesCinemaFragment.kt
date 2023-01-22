package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.service.autofill.Validators.or
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.CinemasCinemaMoviesAdapter
import com.flexath.themoviebookingapp.ui.adapters.movies.DateCardsCinemaMoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_cinema.*


class MoviesCinemaFragment : Fragment() {

    private lateinit var mDateCardsAdapter:DateCardsCinemaMoviesAdapter
    private lateinit var mCinemasAdapter: CinemasCinemaMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_cinema, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpDateCardsRecyclerView()            // For Date Cards
        setUpCinemasRecyclerView()
    }

    private fun setUpDateCardsRecyclerView() {
        mDateCardsAdapter = DateCardsCinemaMoviesAdapter()
        rvDatesCardsMoviesCinema.adapter = mDateCardsAdapter
        rvDatesCardsMoviesCinema.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        mDateCardsAdapter.notifyDataSetChanged()
    }

    private fun setUpCinemasRecyclerView() {
        mCinemasAdapter = CinemasCinemaMoviesAdapter()
        rvCinemasMoviesCinema.adapter = mCinemasAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        rvCinemasMoviesCinema.layoutManager = linearLayoutManager
        mCinemasAdapter.notifyDataSetChanged()

//        val divider = DividerItemDecoration(requireContext(),linearLayoutManager.orientation)
//        divider.setDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.item_cinema_separator)!!)
//        rvCinemasMoviesCinema.addItemDecoration(divider)
    }
}