package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
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
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        mMoviesAdapter = MoviesHomeRecyclerAdapter(args.argTabPosition,this)
        rvMoviesSearch.adapter = mMoviesAdapter
        rvMoviesSearch.layoutManager = GridLayoutManager(requireContext(), 2)
        mMoviesAdapter.notifyDataSetChanged()
    }

    private fun setUpListeners() {
//        searchViewMoviesHome.setOnQueryTextListener(object : OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                searchMovies(newText)
//                return true
//            }
//
//        })

        if(args.argTabPosition == 0){
            btnFilterMonthMoviesSearch.visibility = View.GONE
        }

    }

//    private fun searchMovies(newText: String?) {
//        val movieListAfterSearching = mutableListOf<MovieVO>()
//        for(movie in movieListBeforeSearching) {
//            if(newText?.let { movie.title?.contains(it) } == true) {
//                movieListAfterSearching.add(movie)
//            }
//        }
//        mMoviesAdapter.setNewMovieList(movieListAfterSearching)
//    }

    override fun onTapMovie(movieId: Int?) {
        val action = MoviesSearchFragmentDirections.actionMoviesSearchToMoviesDetailsHome()
        action.argNowShowingOrComingSoon = args.argTabPosition
        findNavController().navigate(action)
    }
}