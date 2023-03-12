package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.network.utils.BASE_URL
import com.flexath.themoviebookingapp.network.utils.IMG_BASE_URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_confirmation.*
import kotlinx.android.synthetic.main.layout_ticket_confirmation_details.*

class MoviesTicketConfirmationFragment : Fragment() {

    private val args:MoviesTicketConfirmationFragmentArgs by navArgs()
    private val mCinemaModel:CinemaModel = CinemaModelImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_ticket_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpListeners()
        bindData()
    }

    private fun bindData() {
        Log.i("TicketConfirm",args.argCheckoutTicket.toString())

        tvCinemaNameMoviesConfirmation.text = args.argCheckoutTicket?.bookingNo ?: ""
        tvNumberOfTicketConfirmation.text = args.argCheckoutTicket?.totalSeat.toString()
        tvTicketNamesConfirmation.text = args.argCheckoutTicket?.seat ?: ""
        tvDateConfirmation.text = args.argCheckoutTicket?.bookingDate ?: ""
        tvTimeConfirmation.text = args.argCheckoutTicket?.timeslot?.start_time ?: ""
        tvAddressConfirmation.text = args.argAddress ?: ""

        val movie = mCinemaModel.getMovieByIdForTicket(args.argCheckoutTicket?.movieId.toString())
        tvMovieTitleConfirmation.text = movie?.originalTitle ?: ""

        Glide.with(requireActivity())
            .load("$IMG_BASE_URL${movie?.posterPath}")
            .into(ivMoviePosterConfirmation)

        Glide.with(requireActivity())
            .load("$BASE_URL/${args.argCheckoutTicket?.qrCode}")
            .into(ivQRCodeMoviesTicketConfirmation)

    }

    private fun setUpListeners() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            ivBookingSuccessMoviesTicketConfirmation?.visibility = View.INVISIBLE
            if(ivBookingSuccessMoviesTicketConfirmation != null) {
                Toast.makeText(requireContext(),"Done!",Toast.LENGTH_SHORT).show()
            }
        },3000)

        btnDoneMoviesTicketConfirmation.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.popBackStack(R.id.moviesHomeFragment, true)
            navController.navigate(R.id.moviesHomeFragment)
        }
    }
}