package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.DateCardsCinemaMoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_choose_cinema.*

class ChooseCinemaFragment : Fragment() {

    private lateinit var mDateCardsAdapter:DateCardsCinemaMoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_cinema, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpDateCardsRecyclerView()
    }

    private fun setUpDateCardsRecyclerView() {
        mDateCardsAdapter = DateCardsCinemaMoviesAdapter()
        rvDatesCardsMoviesCinema.adapter = mDateCardsAdapter
        rvDatesCardsMoviesCinema.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        mDateCardsAdapter.notifyDataSetChanged()
    }
}