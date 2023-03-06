package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesHomeRecyclerAdapter
import com.flexath.themoviebookingapp.ui.delegates.MoviesListViewHolderDelegate
import kotlinx.android.synthetic.main.fragment_tab_layout_movies_home.*

class TabLayoutMoviesHomeFragment(private val position: Int, private val mMovieModel: CinemaModel) :
    Fragment(),
    MoviesListViewHolderDelegate {

    private lateinit var mMoviesHomeAdapter: MoviesHomeRecyclerAdapter

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
        getMoviesByTabPosition(position)
    }

    private fun getMoviesByTabPosition(position: Int) {
        if(position == 0) {
            mMovieModel.getNowPlayingMovies(
                onSuccess = {
                    Log.i("NowPlaying",it.toString())
                    mMoviesHomeAdapter.bindNewData(it)
                },
                onFailure = {
                    Toast.makeText(requireContext(),"Internet Connection is offline",Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            mMovieModel.getComingSoonMovies(
                onSuccess = {
                    mMoviesHomeAdapter.bindNewData(it)
                },
                onFailure = {
                    Toast.makeText(requireContext(),"Internet Connection is offline",Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpMoviesHomeRecyclerView() {
        mMoviesHomeAdapter = MoviesHomeRecyclerAdapter(position, this)
        rvMoviesHome.adapter = mMoviesHomeAdapter
        rvMoviesHome.layoutManager = GridLayoutManager(requireContext(), 2)
        mMoviesHomeAdapter.notifyDataSetChanged()
    }

    override fun onTapMovie(movieId: Int?) {
        val action = MoviesHomeFragmentDirections.actionMoviesHomeToMoviesDetailsHome()
        action.argNowShowingOrComingSoon = position
        movieId?.let {
            action.argMovieId = it
        }
        findNavController().navigate(action)
    }
}