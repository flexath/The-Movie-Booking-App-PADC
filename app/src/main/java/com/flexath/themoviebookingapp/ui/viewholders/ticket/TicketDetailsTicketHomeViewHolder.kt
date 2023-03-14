package com.flexath.themoviebookingapp.ui.viewholders.ticket

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flexath.themoviebookingapp.data.vos.ticket.TicketInformation
import com.flexath.themoviebookingapp.network.utils.BASE_URL
import com.flexath.themoviebookingapp.network.utils.IMG_BASE_URL
import com.flexath.themoviebookingapp.ui.fragments.tickets.TicketsHomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_movies_ticket_confirmation.*
import kotlinx.android.synthetic.main.fragment_movies_ticket_confirmation.view.*
import kotlinx.android.synthetic.main.layout_ticket_confirmation_details.*
import kotlinx.android.synthetic.main.layout_ticket_confirmation_details.view.*

class TicketDetailsTicketHomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mTicket:TicketInformation? = null

    init {
        cancelBooking()
    }

    private fun cancelBooking(){
        itemView.setOnClickListener {
            val action = TicketsHomeFragmentDirections.actionTicketsHomeToMoviesTicketCheckout()
            action.argCheckoutOrCancel = "Cancel"
            action.argCheckoutTicketCancel = mTicket
            it.findNavController().navigate(action)
        }
    }

    fun bindData(ticket: TicketInformation?) {

        mTicket = ticket

        itemView.tvMovieTitleConfirmation.text =  ticket?.movieName ?: ""
        itemView.tvCinemaNameMoviesConfirmation.text = ticket?.ticketCheckout?.bookingNo ?: ""
        itemView.tvNumberOfTicketConfirmation.text = ticket?.ticketCheckout?.totalSeat.toString()
        itemView.tvTicketNamesConfirmation.text = ticket?.ticketCheckout?.seat ?: ""
        itemView.tvDateConfirmation.text = ticket?.ticketCheckout?.bookingDate ?: ""
        itemView.tvTimeConfirmation.text = ticket?.ticketCheckout?.timeslot?.start_time ?: ""
        itemView.tvAddressConfirmation.text = ticket?.address ?: ""

        Glide.with(itemView.context)
            .load("$IMG_BASE_URL${ticket?.moviePoster ?: ""}")
            .into(itemView.ivMoviePosterConfirmation)
    }
}