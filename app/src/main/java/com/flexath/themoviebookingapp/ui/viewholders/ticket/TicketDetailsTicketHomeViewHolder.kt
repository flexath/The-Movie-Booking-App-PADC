package com.flexath.themoviebookingapp.ui.viewholders.ticket

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.ui.fragments.tickets.TicketsHomeFragmentDirections

class TicketDetailsTicketHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        cancelBooking()
    }

    private fun cancelBooking(){
        itemView.setOnClickListener {
            val action = TicketsHomeFragmentDirections.actionTicketsHomeToMoviesTicketCheckout()
            action.argCheckoutOrCancel = "Cancel"
            it.findNavController().navigate(action)
        }

    }
}