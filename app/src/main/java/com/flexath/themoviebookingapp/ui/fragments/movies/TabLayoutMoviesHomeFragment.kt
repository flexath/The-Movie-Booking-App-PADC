package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.MoviesHomeRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_tab_layout_movies_home.*

class TabLayoutMoviesHomeFragment : Fragment() {

    private lateinit var mMoviesHomeAdapter:MoviesHomeRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_layout_movies_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMoviesHomeRecyclerView()
    }

    private fun setUpMoviesHomeRecyclerView(){
        mMoviesHomeAdapter = MoviesHomeRecyclerAdapter()
        rvMoviesHome.adapter = mMoviesHomeAdapter
        rvMoviesHome.layoutManager = GridLayoutManager(requireContext(),2)
    }
}