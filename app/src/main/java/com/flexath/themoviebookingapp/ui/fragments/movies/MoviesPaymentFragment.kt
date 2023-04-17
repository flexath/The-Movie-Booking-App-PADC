package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.model.CinemaModel
import com.flexath.themoviebookingapp.data.model.CinemaModelImpl
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.CheckoutBody
import com.flexath.themoviebookingapp.ui.adapters.movies.MoviesPaymentAdapter
import com.flexath.themoviebookingapp.ui.delegates.PaymentViewHolderDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_payment.*

class MoviesPaymentFragment : Fragment(),PaymentViewHolderDelegate {

    private lateinit var mPaymentAdapter:MoviesPaymentAdapter
    private val mCinemaModel:CinemaModel = CinemaModelImpl

    private val args: MoviesPaymentFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        Log.i("Ticketering",args.argTicketCheckout.toString())
        Log.i("TicketeringSnack",args.argTicketCheckout?.snackList.toString())
        Log.i("TicketeringSnackSize",args.argTicketCheckout?.snackList?.size.toString())

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

    private fun getTicketList() : String {
        var seatNumberString = ""
        for (seatNumber in args.argTicketCheckout?.seatInfo?.ticketList ?: mutableListOf()) {
            seatNumberString += "$seatNumber,"
        }

        if(seatNumberString.isNotEmpty()){
            seatNumberString = StringBuilder(seatNumberString).also {
                it.deleteCharAt(it.lastIndex)
            }.toString()
        }
        return seatNumberString
    }

    private fun getTicketCheckoutBody(paymentId: Int): CheckoutBody {
        return CheckoutBody(
            args.argTicketCheckout?.movieId?.toInt(),
            args.argTicketCheckout?.cinemaInfo?.date,
            args.argTicketCheckout?.cinemaInfo?.cinemaTimeslotId,
            getTicketList(),
            args.argCheckoutBodySnackPayment?.snackList,
            paymentId
        )
    }

    private fun setUpPaymentRecyclerVIew() {
        mPaymentAdapter = MoviesPaymentAdapter(this)
        rvPaymentMovies.adapter = mPaymentAdapter
        rvPaymentMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onTapPayment(paymentId: Int) {
        mCinemaModel.getTicketCheckout(
            "Bearer ${mCinemaModel.getOtp(201)?.token}",
            getTicketCheckoutBody(paymentId),
            onSuccess = {
                val action = MoviesPaymentFragmentDirections.actionProfilePaymentToMoviesTicketConfirmation()
                action.argCheckoutTicket = it
                action.argTicketCheckout = args.argTicketCheckout
                action.argAddress = args.argTicketCheckout?.cinemaInfo?.address
                findNavController().navigate(action)
            },
            onFailure = {
                Toast.makeText(requireActivity(),"Checkout call fails",Toast.LENGTH_SHORT).show()
            }
        )
    }
}