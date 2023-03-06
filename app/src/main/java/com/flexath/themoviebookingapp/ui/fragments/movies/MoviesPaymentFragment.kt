package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesPaymentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_payment.*

class MoviesPaymentFragment : Fragment() {

    private lateinit var mPaymentAdapter:MoviesPaymentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpPaymentRecyclerVIew()
    }

    private fun setUpPaymentRecyclerVIew() {
        mPaymentAdapter = MoviesPaymentAdapter()
        rvPaymentMovies.adapter = mPaymentAdapter
        rvPaymentMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun navigateNextFragment(view: View) {
        val action = MoviesPaymentFragmentDirections.actionProfilePaymentToMoviesTicketConfirmation()
        view.findNavController().navigate(action)
    }
}