package com.flexath.themoviebookingapp.ui.adapters.ticket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.viewholders.ticket.TicketDetailsTicketHomeViewHolder

class TicketDetailsTicketHomeAdapter : RecyclerView.Adapter<TicketDetailsTicketHomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketDetailsTicketHomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_ticket_confirmation_details,parent,false)
        return TicketDetailsTicketHomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketDetailsTicketHomeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}