package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.MovieVO
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

        setUpRecyclerView()
        setUpListeners()

        Log.i("TabPositioner",args.argTabPosition.toString())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        mMoviesAdapter = MoviesHomeRecyclerAdapter(args.argTabPosition,this)
        rvMoviesSearch.adapter = mMoviesAdapter
        rvMoviesSearch.layoutManager = GridLayoutManager(requireActivity(), 2)
        mMoviesAdapter.notifyDataSetChanged()
    }

    private fun setUpListeners() {
        btnAppBarBackMoviesSearch.setOnClickListener {
            findNavController().popBackStack()
        }

        searchViewMoviesHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(args.argTabPosition == 0) {
                    searchMovies(newText,args.argMovieListNowShowing?.movieList ?: listOf())
                } else {
                    searchMovies(newText,args.argMovieListComingSoon?.movieList ?: listOf())
                }
                return true
            }

        })

        if(args.argTabPosition == 0){
            btnFilterMonthMoviesSearch.visibility = View.GONE
        }

    }

    private fun searchMovies(newText: String?,movieList: List<MovieVO>) {
        val movieListAfterSearching = mutableListOf<MovieVO>()
        for (movie in movieList) {
            if (newText?.let { movie.originalTitle?.contains(it) } == true) {
                movieListAfterSearching.add(movie)
            }
        }
        mMoviesAdapter.bindNewData(movieListAfterSearching)
    }

    override fun onTapMovie(movieId: Int?) {
        val action = MoviesSearchFragmentDirections.actionMoviesSearchToMoviesDetailsHome()
        action.argNowShowingOrComingSoon = args.argTabPosition
        if (movieId != null) {
            action.argMovieId = movieId
        }
        findNavController().navigate(action)
    }
}