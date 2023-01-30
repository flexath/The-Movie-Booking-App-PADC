package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.OrderedFoodMoviesTicketCheckoutAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_checkout.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_checkout.view.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog_movies_checkout.*

class MoviesTicketCheckoutFragment : Fragment() {

    private lateinit var mOrderedFoodAdapter: OrderedFoodMoviesTicketCheckoutAdapter
    private var isVisibleRecyclerView:Boolean = true

    private val args:MoviesTicketCheckoutFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_ticket_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).bottomNvgViewHome.visibility = View.INVISIBLE

        setUpOrderedFoodListRecyclerView()              // For Ordered Food List
        hasItemInRecyclerView()                         // For visibility of Order Food List

        setUpListeners()
    }

    private fun setUpListeners() {

        if(args.argCheckoutOrCancel == "Checkout"){
            rlTicketCancellationMoviesCheckout.visibility = View.GONE
            btnContinueMoviesTicketCheckout.visibility = View.VISIBLE
            btnTicketCancellationMoviesTicketCheckout.setImageResource(R.drawable.ticket_cancellation_button_policy)

            btnContinueMoviesTicketCheckout.setOnClickListener {
                val action = MoviesTicketCheckoutFragmentDirections.actionMoviesTicketCheckoutToProfilePayment()
                it.findNavController().navigate(action)
            }
        }else{
            rlTicketCancellationMoviesCheckout.visibility = View.VISIBLE
            btnContinueMoviesTicketCheckout.visibility = View.GONE
            btnTicketCancellationMoviesTicketCheckout.setImageResource(R.drawable.ticket_cancellation_policy_button)

            btnCancelButtonMoviesCancel.setOnClickListener {
                Toast.makeText(requireContext(),"Ticket's cancelled",Toast.LENGTH_SHORT).show()
            }
        }

        btnTicketCancellationMoviesTicketCheckout.setOnClickListener {
            val bottomDialog = BottomSheetDialog(requireActivity())
            val dialogView = layoutInflater.inflate(R.layout.layout_bottom_sheet_dialog_movies_checkout,null,false)
            bottomDialog.setContentView(dialogView)
            bottomDialog.setCancelable(true)
            bottomDialog.show()

            bottomDialog.btnCloseButtonBottomSheetDialog.setOnClickListener {
                bottomDialog.dismiss()
            }
        }
    }

    private fun hasItemInRecyclerView() {
        if(mOrderedFoodAdapter.itemCount == 0){
            llFoodMoviesTicketCheckout.visibility = View.GONE
        }else{
            llFoodMoviesTicketCheckout.visibility = View.VISIBLE
            btnFoodAndBeverageMoviesTicketCheckout.setOnClickListener {
                if(isVisibleRecyclerView){
                    rvFoodOrderedListMoviesTicketCheckout.visibility = View.GONE
                    btnFoodAndBeverageMoviesTicketCheckout.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_white_24dp)
                    isVisibleRecyclerView = false
                }else{
                    rvFoodOrderedListMoviesTicketCheckout.visibility = View.VISIBLE
                    btnFoodAndBeverageMoviesTicketCheckout.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_white_24dp)
                    isVisibleRecyclerView = true
                }
            }
        }
    }

    private fun setUpOrderedFoodListRecyclerView() {
        mOrderedFoodAdapter = OrderedFoodMoviesTicketCheckoutAdapter()
        rvFoodOrderedListMoviesTicketCheckout.adapter = mOrderedFoodAdapter
        rvFoodOrderedListMoviesTicketCheckout.layoutManager = LinearLayoutManager(requireContext())
        rvFoodOrderedListMoviesTicketCheckout.setHasFixedSize(true)
        mOrderedFoodAdapter.notifyDataSetChanged()
    }
}