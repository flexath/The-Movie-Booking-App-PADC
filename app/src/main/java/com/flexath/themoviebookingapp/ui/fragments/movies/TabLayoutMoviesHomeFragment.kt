package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesHomeRecyclerAdapter
import com.flexath.themoviebookingapp.ui.delegates.MoviesListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.utils.MovieSearchData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_movies_home.*
import kotlinx.android.synthetic.main.fragment_tab_layout_movies_home.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_home.*

class TabLayoutMoviesHomeFragment(private var position:Int,private val mMovieModel: CinemaModel,private val fragment:Fragment) :
    Fragment(),
    MoviesListViewHolderDelegate {

    private lateinit var mMoviesHomeAdapter: MoviesHomeRecyclerAdapter
    private var mMovieListNowShowing:MutableList<MovieVO> = mutableListOf()
    private var mMovieListComingSoon:MutableList<MovieVO> = mutableListOf()

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
        setUpListeners()
    }

    private fun setUpListeners() {
        fragment.btnSearchMoviesHome.setOnClickListener {
            val action = MoviesHomeFragmentDirections.actionMoviesHomeToMoviesSearch()
            action.argTabPosition = fragment.tabLayoutMoviesHome.selectedTabPosition
            action.argMovieListNowShowing = MovieSearchData(mMovieListNowShowing)
            action.argMovieListComingSoon = MovieSearchData(mMovieListComingSoon)
            it.findNavController().navigate(action)
        }
    }

    private fun requestData(pos:Int) {
        if(pos == 0) {
            mMovieModel.getNowPlayingMovies(
                onSuccess = {
                    mMovieListNowShowing = it as MutableList<MovieVO>
                    mMoviesHomeAdapter.bindNewData(it)
                },
                onFailure = {
                    Toast.makeText(requireContext(),"Internet Connection is offline",Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            mMovieModel.getComingSoonMovies(
                onSuccess = {
                    mMovieListComingSoon = it as MutableList<MovieVO>
                    mMoviesHomeAdapter.bindNewData(it)
                },
                onFailure = {
                    Toast.makeText(requireContext(),"Internet Connection is offline",Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun getMoviesByTabPosition(tabPosition: Int) {

        requestData(tabPosition)

        fragment.tabLayoutMoviesHome.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    requestData(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


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