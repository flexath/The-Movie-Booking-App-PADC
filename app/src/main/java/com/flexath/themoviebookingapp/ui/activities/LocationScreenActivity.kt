package com.flexath.themoviebookingapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.CitiesLocationAdapter
import com.flexath.themoviebookingapp.ui.delegates.CitiesViewHolderDelegate
import com.flexath.themoviebookingapp.ui.dummy.CitiesData
import kotlinx.android.synthetic.main.activity_location_screen.*

class LocationScreenActivity : AppCompatActivity(),CitiesViewHolderDelegate {

    private lateinit var mCitiesAdapter: CitiesLocationAdapter

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

    private fun setUpCitiesRecyclerView() {
        mCitiesAdapter = CitiesLocationAdapter(CitiesData.citiesList,this)
        rvCitiesLocation.adapter = mCitiesAdapter
        rvCitiesLocation.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvCitiesLocation.setHasFixedSize(true)
        mCitiesAdapter.notifyDataSetChanged()
    }

    override fun onTapCities(position:Int) {
        mCitiesAdapter.notifyDataSetChanged()
        MainActivity.newIntentFromMainActivity(this,CitiesData.citiesList[position]).also {
            startActivity(it)
        }
    }
}