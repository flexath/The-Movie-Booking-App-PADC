package com.flexath.themoviebookingapp.ui.fragments.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.movies.OrderedFoodMoviesTicketCheckoutAdapter
import kotlinx.android.synthetic.main.fragment_movies_ticket_checkout.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_checkout.view.*

class MoviesTicketCheckoutFragment : Fragment() {

    private lateinit var mOrderedFoodAdapter: OrderedFoodMoviesTicketCheckoutAdapter
    private var isVisibleRecyclerView:Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_ticket_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOrderedFoodListRecyclerView()              // For Ordered Food List
        hasItemInRecyclerView()                         // For visibility of Order Food List

        setUpListeners()
    }

    private fun setUpListeners() {
        btnContinueMoviesTicketCheckout.setOnClickListener {
            val action = MoviesTicketCheckoutFragmentDirections.actionMoviesTicketCheckoutToMoviesTicketConfirmation()
            it.findNavController().navigate(action)
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