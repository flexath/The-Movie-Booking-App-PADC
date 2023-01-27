package com.flexath.themoviebookingapp.ui.fragments.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.themoviebookingapp.R
import com.flexath.themoviebookingapp.ui.adapters.ticket.TicketDetailsTicketHomeAdapter
import kotlinx.android.synthetic.main.fragment_tickets_home.*

class TicketsHomeFragment : Fragment() {

    private lateinit var mTicketAdapter:TicketDetailsTicketHomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tickets_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTicketRecyclerView()
    }

    private fun setUpTicketRecyclerView() {
        mTicketAdapter = TicketDetailsTicketHomeAdapter()
        rvTicketHome.adapter = mTicketAdapter
        rvTicketHome.layoutManager = LinearLayoutManager(requireContext())
        rvTicketHome.setHasFixedSize(true)
        mTicketAdapter.notifyDataSetChanged()
    }
}