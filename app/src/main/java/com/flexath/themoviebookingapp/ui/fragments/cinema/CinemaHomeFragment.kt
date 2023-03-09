package com.flexath.themoviebookingapp.ui.fragments.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewpods.CinemaListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cinema_home.*
import kotlinx.android.synthetic.main.layout_app_bar_cinema_home.*

class CinemaHomeFragment : Fragment(),CinemaListViewHolderDelegate {

    private lateinit var cinemaListViewPod: CinemaListViewPod

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinema_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.VISIBLE

        setUpAppBarListeners()
        setUpViewPod()
    }

    private fun setUpAppBarListeners() {
        tvAppBarCityNameCinemaHome.text = (activity as AppCompatActivity).intent.getStringExtra(MainActivity.CITY_NAME_EXTRA)

        btnAppBarSearchCinemaHome.setOnClickListener {
            val action = CinemaHomeFragmentDirections.actionCinemaHomeToCinemaSearch()
            it.findNavController().navigate(action)
        }
    }

    private fun setUpViewPod() {
        cinemaListViewPod = vpCinemaHome as CinemaListViewPod
        cinemaListViewPod.setUpMovieListViewPod(this)
    }

    override fun onClickCinemaSeeDetails(cinemaId: Int) {
        val action = CinemaHomeFragmentDirections.actionCinemaHomeToCinemaInfo()
        findNavController().navigate(action)
    }

    override fun onClickCinemaTimes(dayTimeslotId:Int) {
        val action = CinemaHomeFragmentDirections.actionCinemaHomeToMoviesSeat()
        findNavController().navigate(action)
    }

    override fun onClickTimeSlot(date:String) {

    }
}