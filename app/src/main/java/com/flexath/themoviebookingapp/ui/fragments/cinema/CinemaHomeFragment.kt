package com.flexath.themoviebookingapp.ui.fragments.cinema

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.viewpods.CinemaListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_cinema_home.*
import kotlinx.android.synthetic.main.layout_app_bar_cinema_home.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_home.*

class CinemaHomeFragment : Fragment(),CinemaListViewHolderDelegate {

    private lateinit var cinemaListViewPod: CinemaListViewPod
    private val mCinemaModel:CinemaModel = CinemaModelImpl
    private lateinit var sharedPref:SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinema_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.VISIBLE

        sharedPref = activity?.getSharedPreferences("CITY_PREF", Context.MODE_PRIVATE)!!

        setUpAppBarListeners()
        setUpViewPod()
        requestData()
    }

    private fun setUpAppBarListeners() {
        val city = (activity as AppCompatActivity).intent.getStringExtra(MainActivity.CITY_NAME_EXTRA)
        tvAppBarCityNameCinemaHome.text = city ?: ""

        if(city == null) {
            val citySharedPref = sharedPref.getString("city", "")
            tvAppBarCityNameCinemaHome.text = citySharedPref
        } else {
            val editor = sharedPref.edit()
            editor?.putString("city", city)
            editor?.apply()
        }

        btnAppBarSearchCinemaHome.setOnClickListener {
            val action = CinemaHomeFragmentDirections.actionCinemaHomeToCinemaSearch()
            it.findNavController().navigate(action)
        }
    }

    private fun setUpViewPod() {
        cinemaListViewPod = vpCinemaHome as CinemaListViewPod
        cinemaListViewPod.setUpMovieListViewPod(this)
    }

    private fun requestData() {
        mCinemaModel.getCinemaTimeSlots(
            "Bearer ${mCinemaModel.getOtp(201)?.token}",
            "2023-03-17",
            onSuccess = {
                cinemaListViewPod.setNewData(it)
            },
            onFailure = {
                Toast.makeText(requireContext(), "This function", Toast.LENGTH_SHORT).show()
            }
        )
    }


    override fun onClickCinemaSeeDetails(cinemaId: Int) {
        val action = CinemaHomeFragmentDirections.actionCinemaHomeToCinemaInfo()
        action.argCinemaId = cinemaId
        findNavController().navigate(action)
    }

    override fun onClickCinemaTimes(dayTimeslotId:Int, cinemaTime: String?) {
        val action = CinemaHomeFragmentDirections.actionCinemaHomeToMoviesSeat()
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