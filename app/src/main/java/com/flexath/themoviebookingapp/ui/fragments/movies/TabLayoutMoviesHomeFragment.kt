package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.MoviesHomeRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_tab_layout_movies_home.*

class TabLayoutMoviesHomeFragment : Fragment() {

    private lateinit var mMoviesHomeAdapter: MoviesHomeRecyclerAdapter

    companion object {
        const val CINEMA_TIME_EXTRA_KEY: String = "TIME_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_layout_movies_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpMoviesHomeRecyclerView()

    }

    fun getMovieDateBundle() : Bundle? {
        val bundle: Bundle? = arguments.takeIf {
            it?.containsKey(CINEMA_TIME_EXTRA_KEY) ?: false
        }
        return bundle
    }

    private fun setUpMoviesHomeRecyclerView() {
        mMoviesHomeAdapter = MoviesHomeRecyclerAdapter(this)
        rvMoviesHome.adapter = mMoviesHomeAdapter
        rvMoviesHome.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}