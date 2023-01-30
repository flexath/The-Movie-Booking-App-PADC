package com.flexath.themoviebookingapp.ui.fragments.cinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.activities.MainActivity
import kotlinx.android.synthetic.main.layout_app_bar_cinema_home.*

class CinemaHomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinema_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAppBarListeners()
    }

    private fun setUpAppBarListeners() {
        tvCityNameCinemaHome.text = (activity as AppCompatActivity).intent.getStringExtra(MainActivity.CITY_NAME_EXTRA)
    }
}