package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesPaymentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_payment.*

class MoviesPaymentFragment : Fragment() {

    private lateinit var mPaymentAdapter:MoviesPaymentAdapter
    private val mCinemaModel:CinemaModel = CinemaModelImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpPaymentRecyclerVIew()
        requestData()
    }

    private fun requestData() {
        mCinemaModel.getPaymentTypes(
            "Bearer ${mCinemaModel.getOtp(201)?.token}",
            onSuccess = {
                mPaymentAdapter.setNewData(it)
            },
            onFailure = {
                Toast.makeText(requireActivity(),"Payment call fails",Toast.LENGTH_SHORT).show()
            }
        )
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