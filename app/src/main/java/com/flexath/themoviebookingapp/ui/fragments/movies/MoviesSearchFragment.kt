package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesHomeRecyclerAdapter
import com.flexath.themoviebookingapp.ui.delegates.MoviesListViewHolderDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_search.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_search.*

class MoviesSearchFragment : Fragment(),MoviesListViewHolderDelegate {

    private lateinit var mMoviesAdapter:MoviesHomeRecyclerAdapter
    private val args:MoviesSearchFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpListeners()
    }

    private fun setUpRecyclerView() {
        mMoviesAdapter = MoviesHomeRecyclerAdapter(args.argTabPosition,this)
        rvMoviesSearch.adapter = mMoviesAdapter
        rvMoviesSearch.layoutManager = GridLayoutManager(requireContext(), 2)
        mMoviesAdapter.notifyDataSetChanged()
    }

    override fun onTapMovie() {
        val action = MoviesSearchFragmentDirections.actionMoviesSearchToMoviesDetailsHome()
        action.argNowShowingOrComingSoon = args.argTabPosition
        findNavController().navigate(action)
    }

    private fun setUpListeners() {
        etCinemaNameMoviesSearch.setOnClickListener {
            rvMoviesSearch.visibility = View.VISIBLE
            setUpRecyclerView()
        }

        if(args.argTabPosition == 0){
            btnFilterMonthMoviesSearch.visibility = View.GONE
        }

//        if(args.argTabPosition == 0){
//            spinnerFilterMonthMoviesSearch.visibility = View.GONE
//        }
    }
}