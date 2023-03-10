package com.flexath.themoviebookingapp.ui.fragments.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.ui.adapters.movies.SnackTicketCheckoutAdapter
import com.flexath.themoviebookingapp.ui.utils.Ticket
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_checkout.*
import kotlinx.android.synthetic.main.layout_app_bar_movies_ticket_checkout.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog_movies_checkout.*

class MoviesTicketCheckoutFragment : Fragment() {

    private lateinit var mOrderedFoodAdapter: SnackTicketCheckoutAdapter
    private var isVisibleRecyclerView: Boolean = true

    private var mMovieName: String? = null
    private var mCinemaName: String? = null
    private var mTicketDate: String? = null
    private var mTicketTime: String? = null
    private var mCinemaAddress: String? = null
    private var mNumberOfTicket: Int? = null
    private var mTicketTotalPrice: Long? = 0L
    private var mSnackTotalPrice: Long = 0L
    private var mTicketList: MutableList<String> = mutableListOf()
    private lateinit var mSnackList: List<SnackVO>

    private val args: MoviesTicketCheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_ticket_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        Log.i("CinemaCheckout", args.argTicket.toString())

        mMovieName = args.argTicket?.movieName
        mCinemaName = args.argTicket?.cinemaInfo?.cinemaName
        mTicketDate = args.argTicket?.cinemaInfo?.date
        mTicketTime = args.argTicket?.cinemaInfo?.time
        mCinemaAddress = args.argTicket?.cinemaInfo?.address
        mNumberOfTicket = args.argTicket?.seatInfo?.numberOfTicket
        mTicketTotalPrice = args.argTicket?.seatInfo?.ticketTotalPrice
        mSnackTotalPrice = args.argTicket?.snackTotalPrice ?: 0
        mTicketList = args.argTicket?.seatInfo?.ticketList ?: mutableListOf()
        mSnackList = args.argTicket?.snackList ?: listOf()

        setUpOrderedFoodListRecyclerView()

        setUpListeners()
        bindTicketData()
        hasItemInRecyclerView()
    }

    private fun bindTicketData() {
        tvMovieNameTicket.text = mMovieName
        tvCinemaNameTicket.text = mCinemaName
        tvDateTicket.text = mTicketDate
        tvTimeTicket.text = mTicketTime
        tvAddressTicket.text = mCinemaAddress
        tvNumberOfTicket.text = mNumberOfTicket.toString()
        tvSnackTotalPrice.text = mSnackTotalPrice.toString()

        val ticketTotalPrice = "${mTicketTotalPrice}Ks"
        tvTicketTotalPrice.text = ticketTotalPrice

        tvTicketNamesTicket.text = getTicketList()

        val totalMoney = "${((mTicketTotalPrice?.plus(mSnackTotalPrice) ?: 0) + 500)}Ks"
        tvTotalMoney.text = totalMoney

        mOrderedFoodAdapter.bindNewData(setUpSnackList())
    }

    private fun setUpSnackList() : MutableList<SnackVO> {
        val snackList = mutableListOf<SnackVO>()
        for(snack in mSnackList) {
            if(snack.quantity > 0) {
                snackList.add(snack)
            }
        }
        return snackList
    }

    private fun getTicketList() : String {
        var ticket = ""
        if(mTicketList.isNotEmpty()) {
            mTicketList.forEach {
                ticket += "$it,"
            }
            ticket = StringBuilder(ticket).also {
                it.deleteCharAt(it.lastIndex)
            }.toString()
        }
        return ticket
    }

    @SuppressLint("InflateParams")
    private fun setUpListeners() {

        if (args.argCheckoutOrCancel == "Checkout") {
            rlTicketCancellationMoviesCheckout.visibility = View.GONE
            btnContinueMoviesTicketCheckout.visibility = View.VISIBLE
            btnTicketCancellationMoviesTicketCheckout.setImageResource(R.drawable.ticket_cancellation_button_policy)

            btnContinueMoviesTicketCheckout.setOnClickListener {
                val action = MoviesTicketCheckoutFragmentDirections.actionMoviesTicketCheckoutToProfilePayment()
                action.argTicketCheckout = args.argTicket
                action.argCheckoutBodySnackPayment = args.argCheckoutBodySnackCheckout
                it.findNavController().navigate(action)
            }
        } else {
            rlTicketCancellationMoviesCheckout.visibility = View.VISIBLE
            btnContinueMoviesTicketCheckout.visibility = View.GONE
            btnTicketCancellationMoviesTicketCheckout.setImageResource(R.drawable.ticket_cancellation_policy_button)
            val ticketDetailsTitle = "Ticket Details"
            tvTicketTitleMoviesTicketCheckout.text = ticketDetailsTitle

            btnCancelButtonMoviesCancel.setOnClickListener {
                Toast.makeText(requireContext(), "Ticket's cancelled", Toast.LENGTH_SHORT).show()
            }
        }

        btnTicketCancellationMoviesTicketCheckout.setOnClickListener {
            val bottomDialog = BottomSheetDialog(requireActivity())
            val dialogView = layoutInflater.inflate(
                R.layout.layout_bottom_sheet_dialog_movies_checkout,
                null,
                false
            )
            bottomDialog.setContentView(dialogView)
            bottomDialog.setCancelable(true)
            bottomDialog.show()

            bottomDialog.btnCloseButtonBottomSheetDialog.setOnClickListener {
                bottomDialog.dismiss()
            }
        }
    }

    private fun hasItemInRecyclerView() {
        if (mOrderedFoodAdapter.itemCount == 0) {
            llFoodMoviesTicketCheckout.visibility = View.GONE
        } else {
            llFoodMoviesTicketCheckout.visibility = View.VISIBLE
            btnFoodAndBeverageMoviesTicketCheckout.setOnClickListener {
                if (isVisibleRecyclerView) {
                    rvSnackTicketCheckout.visibility = View.GONE
                    btnFoodAndBeverageMoviesTicketCheckout.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_white_24dp)
                    isVisibleRecyclerView = false
                } else {
                    rvSnackTicketCheckout.visibility = View.VISIBLE
                    btnFoodAndBeverageMoviesTicketCheckout.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_white_24dp)
                    isVisibleRecyclerView = true
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpOrderedFoodListRecyclerView() {
        mOrderedFoodAdapter = SnackTicketCheckoutAdapter()
        rvSnackTicketCheckout.adapter = mOrderedFoodAdapter
        rvSnackTicketCheckout.layoutManager = LinearLayoutManager(requireActivity())
        mOrderedFoodAdapter.notifyDataSetChanged()
    }
}