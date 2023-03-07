package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.ui.adapters.movies.DateCardMoviesCinemaAdapter
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.utils.TimeSlotUtil
import com.flexath.themoviebookingapp.ui.viewpods.CinemaListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_cinema.*


class MoviesCinemaFragment : Fragment(),CinemaListViewHolderDelegate {

    private lateinit var mDateCardsAdapter:DateCardMoviesCinemaAdapter
    private lateinit var timeSlotUtil: TimeSlotUtil

    private lateinit var cinemaListViewPod: CinemaListViewPod
    private val mMovieModel:CinemaModel = CinemaModelImpl

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        timeSlotUtil = TimeSlotUtil()
        timeSlotUtil.addTimeSlotToDateList()
        return inflater.inflate(R.layout.fragment_movies_cinema, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpDateCardsRecyclerView()            // For Date Cards
        bindTimeSlotData()

        setUpViewPod()

        Log.i("DateTimeSlot",timeSlotUtil.dateListTimeSLot.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        super.onDestroy()
        timeSlotUtil.clearDateList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindTimeSlotData() {
        mDateCardsAdapter.bindTimeSlotData(timeSlotUtil.dateList)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpDateCardsRecyclerView() {
        mDateCardsAdapter = DateCardMoviesCinemaAdapter(timeSlotUtil.dateListTimeSLot,this)
        rvDatesCardsMoviesCinema.adapter = mDateCardsAdapter
        rvDatesCardsMoviesCinema.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpViewPod() {
        cinemaListViewPod = vpMoviesCinema as CinemaListViewPod
        cinemaListViewPod.setUpMovieListViewPod(this)
    }

    private fun requestData(date:String) {
        Log.i("AungThiha",date)
        mMovieModel.getCinemaTimeSlots(
            "Bearer 15490|IWB1wCOGR7AnqUANxr7SkXt2P4Vv4I33C5RHpAvx",
            date,
            onSuccess = {
                cinemaListViewPod.setNewData(it)
            },
            onFailure = {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onClickCinemaSeeDetails() {
        val action = MoviesCinemaFragmentDirections.actionChooseCinemaToCinemaInfo()
        findNavController().navigate(action)
    }

    override fun onClickCinemaTimes() {
        val action = MoviesCinemaFragmentDirections.actionChooseCinemaToMoviesSeat()
        findNavController().navigate(action)
    }

    override fun onClickTimeSlot(date:String) {
        requestData(date)
        Toast.makeText(requireContext(),date,Toast.LENGTH_SHORT).show()
    }
}