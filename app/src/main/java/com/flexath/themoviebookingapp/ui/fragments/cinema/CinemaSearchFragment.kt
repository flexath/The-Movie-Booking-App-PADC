package com.flexath.themoviebookingapp.ui.fragments.cinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewpods.CinemaListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cinema_search.*
import kotlinx.android.synthetic.main.layout_app_bar_cinema_search.*

class CinemaSearchFragment : Fragment(),CinemaListViewHolderDelegate {

    private lateinit var cinemaListViewPod: CinemaListViewPod

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinema_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpListeners()
    }

    private fun setUpListeners() {
        etCinemaNameCinemaSearch.setOnClickListener {
            vpCinemaSearch.visibility = View.VISIBLE
            setUpViewPods()
        }
    }

    private fun setUpViewPods() {
        cinemaListViewPod = vpCinemaSearch as CinemaListViewPod
        cinemaListViewPod.setUpMovieListViewPod(this)
    }

    override fun onClickCinemaSeeDetails(cinemaId:Int) {
        val action = CinemaSearchFragmentDirections.actionCinemaSearchToCinemaInfo()
        findNavController().navigate(action)
    }

    override fun onClickCinemaTimes(dayTimeslotId:Int, cinemaTime: String?) {
        val action = CinemaSearchFragmentDirections.actionCinemaSearchToMoviesSeat()
        findNavController().navigate(action)
    }

    override fun onClickTimeSlot(date:String) {

    }

    override fun getCinemaName(cinemaName: String?) {

    }

    override fun getCinemaId(cinemaId: Int?) {

    }

    override fun onClickCinema(cinemaId: Int) {

    }


}