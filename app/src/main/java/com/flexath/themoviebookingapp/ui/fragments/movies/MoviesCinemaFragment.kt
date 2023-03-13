package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.data.vos.movie.cinema.CinemaVO
import com.flexath.themoviebookingapp.ui.adapters.movies.DateCardMoviesCinemaAdapter
import com.flexath.themoviebookingapp.ui.delegates.CinemaListViewHolderDelegate
import com.flexath.themoviebookingapp.ui.utils.CinemaData
import com.flexath.themoviebookingapp.ui.utils.TimeSlotUtil
import com.flexath.themoviebookingapp.ui.viewpods.CinemaListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_cinema.*

class MoviesCinemaFragment : Fragment(), CinemaListViewHolderDelegate {

    private lateinit var mDateCardsAdapter: DateCardMoviesCinemaAdapter
    private lateinit var timeSlotUtil: TimeSlotUtil

    private lateinit var cinemaListViewPod: CinemaListViewPod
    private val mCinemaModel: CinemaModel = CinemaModelImpl
    private var mBookingDate:String? = null
    private var mCinemaList:List<CinemaVO> = listOf()

    private val args:MoviesCinemaFragmentArgs by navArgs()

    // For Ticket
    private var mMovieName:String? = null
    private var mMovieId:String? = null
    private var mCinemaName:String? = null
    private var mCinemaLocation:String? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        timeSlotUtil = TimeSlotUtil()
        timeSlotUtil.addTimeSlotToDateList()
        return inflater.inflate(R.layout.fragment_movies_cinema, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        mMovieName = args.argMovieName
        mMovieId = args.argMovieId

        setUpDateCardsRecyclerView()
        bindTimeSlotData()

        setUpViewPod()
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
        mDateCardsAdapter = DateCardMoviesCinemaAdapter(timeSlotUtil.dateListTimeSLot, this)
        rvDatesCardsMoviesCinema.adapter = mDateCardsAdapter
        rvDatesCardsMoviesCinema.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpViewPod() {
        cinemaListViewPod = vpMoviesCinema as CinemaListViewPod
        cinemaListViewPod.setUpMovieListViewPod(this)
    }

    private fun requestData(date: String) {
        mCinemaModel.getCinemaTimeSlots(
            "Bearer ${mCinemaModel.getOtp(201)?.token}",
            date,
            onSuccess = {
                mCinemaList = it
                cinemaListViewPod.setNewData(it)
            },
            onFailure = {
                Toast.makeText(requireContext(), "This function", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onClickCinemaSeeDetails(cinemaId: Int) {

        val action = MoviesCinemaFragmentDirections.actionChooseCinemaToCinemaInfo()
        action.argCinemaId = cinemaId
        findNavController().navigate(action)
    }

    override fun onClickCinemaTimes(dayTimeslotId:Int,cinemaTime:String?) {
        val action = MoviesCinemaFragmentDirections.actionChooseCinemaToMoviesSeat()
        action.argDayTimeslotId = dayTimeslotId
        action.argBookingDate = mBookingDate

        action.argMovieName = mMovieName
        action.argMovieId = mMovieId
        val mCinemaInfo = CinemaData(mCinemaName,mBookingDate,cinemaTime,mCinemaLocation,dayTimeslotId)
        action.argCinemaInfo = mCinemaInfo
        findNavController().navigate(action)
    }

    override fun onClickTimeSlot(date: String) {
        mBookingDate = date
        requestData(date)
        Toast.makeText(requireContext(), date, Toast.LENGTH_SHORT).show()
    }

    override fun getCinemaName(cinemaName: String?) {
        mCinemaName = cinemaName
    }

    override fun getCinemaId(cinemaId: Int?) {

        cinemaId?.let { id ->
            mCinemaModel.getCinemaInfo(id)?.also { cinemaInfo ->
                mCinemaLocation = cinemaInfo.address
            }
        }
    }

    override fun onClickCinema(cinemaId: Int) {
        mCinemaList.forEach {
            if(cinemaId == it.cinemaId) {
                it.isClicked = true
            } else {
                it.isClicked = false
            }
        }
        cinemaListViewPod.setNewData(mCinemaList)
    }
}