package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.flexath.themoviebookingapp.R
import kotlinx.android.synthetic.main.fragment_movies_seat.*

class MoviesSeatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_seat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
    }

    private fun setUpListeners() {
        btnBuyButtonMoviesSeat.setOnClickListener {
            val action = MoviesSeatFragmentDirections.actionMoviesSeatToMoviesFood()
            it.findNavController().navigate(action)
        }
    }
}