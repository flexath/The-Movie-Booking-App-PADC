package com.flexath.themoviebookingapp.ui.adapters.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.movies.OrderedFoodMoviesTicketCheckoutViewHolder

class OrderedFoodMoviesTicketCheckoutAdapter : RecyclerView.Adapter<OrderedFoodMoviesTicketCheckoutViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderedFoodMoviesTicketCheckoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movies_ticket_checkout_food_orders_list,parent,false)
        return OrderedFoodMoviesTicketCheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderedFoodMoviesTicketCheckoutViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}