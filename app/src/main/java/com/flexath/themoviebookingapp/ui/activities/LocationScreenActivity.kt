package com.flexath.themoviebookingapp.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.adapters.movies.CitiesLocationAdapter
import com.flexath.themoviebookingapp.ui.delegates.CitiesViewHolderDelegate
import kotlinx.android.synthetic.main.activity_location_screen.*

class LocationScreenActivity : AppCompatActivity(),CitiesViewHolderDelegate {

    private lateinit var mCitiesAdapter: CitiesLocationAdapter
    private val mMovieModel: CinemaModel = CinemaModelImpl

    companion object{
        fun newIntentFromLocationScreen(context: Context) : Intent {
            return Intent(context,LocationScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_screen)

        setUpCitiesRecyclerView()
    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpCitiesRecyclerView() {
        mCitiesAdapter = mMovieModel.getCities()?.let {
            CitiesLocationAdapter(it,this)
        }!!
        rvCitiesLocation.adapter = mCitiesAdapter
        rvCitiesLocation.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCitiesLocation.setHasFixedSize(true)
        mCitiesAdapter.notifyDataSetChanged()
    }

    override fun onTapCities(city:String) {
        MainActivity.newIntentFromMainActivity(this,city).also {
            startActivity(it)
        }
    }
}